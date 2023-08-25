package org.crayne.hrm.api.savefile.decrypt;

import me.steinborn.libdeflate.CompressionType;
import me.steinborn.libdeflate.LibdeflateDecompressor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.crayne.hrm.api.level.LevelData;
import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.level.LocalLevelProperties;
import org.crayne.hrm.api.level.data.object.type.LazyLevelObject;
import org.crayne.hrm.api.level.data.settings.LevelSettings;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.zip.DataFormatException;

@SuppressWarnings("unused")
public class LevelDataDecryption {

    private LevelDataDecryption() {}

    public static final int GD_SAVEFILE_XOR_CONSTANT = 11;

    public static byte @NotNull [] xorByteArray(final byte @NotNull [] bytes, @SuppressWarnings("SameParameterValue") final int xorValue) {
        return toByteArray(IntStream.range(0, bytes.length).map(i -> bytes[i] ^ xorValue).toArray());
    }

    private static byte @NotNull [] toByteArray(final int @NotNull [] intBytes) {
        final byte[] bytes = new byte[intBytes.length];
        for (int i = 0; i < bytes.length; i++) bytes[i] = (byte) intBytes[i];
        return bytes;
    }

    private static final int CONST_48_MEGABYTES = 50331648;

    @NotNull
    public static String decompressZlibBytes(final byte @NotNull [] compressedBytes) throws DataFormatException {
        boolean success = false;
        byte[] result;
        int size = CONST_48_MEGABYTES;
        final int finalSize;

        while (true) {
            result = new byte[size]; // virtual memory is efficient, dont worry about it

            try (final LibdeflateDecompressor decompressor = new LibdeflateDecompressor()) {
                final long decompressedSize = decompressor.decompressUnknownSize(compressedBytes, result, CompressionType.GZIP);
                if (decompressedSize >= 0) {
                    finalSize = (int) decompressedSize;
                    break;
                }

                size *= 2; // double the amount of bytes needed if 48mb for a geometry dash level werent enough
                // (even woodkids inner level string can be uncompressed with the usual 48mb limit just fine)
            }
        }
        final byte[] finalResult = Arrays.copyOf(result, finalSize);
        return new String(finalResult, StandardCharsets.UTF_8);

        /* slow implementation but doesnt rely on a max size

        final Inflater decompress = new Inflater(true);
        decompress.setInput(compressedDataHeadless, 0, compressedDataHeadless.length);

        final byte[] buffer = new byte[5];
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (!decompress.finished()) outputStream.write(buffer, 0, decompress.inflate(buffer));
        decompress.end();

        return outputStream.toString(StandardCharsets.UTF_8);*/
    }

    public static byte @NotNull [] decodeBase64(@NotNull final String encoded) {
        return Base64.decodeBase64(encoded.replace('-', '+').replace('_', '/').trim());
    }

    @NotNull
    public static String prettifyXMLData(@NotNull final DefaultElement xmlData, final int indent, final boolean skipDeclaration) throws IOException {
        final OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        prettyFormat.setIndentSize(indent);
        prettyFormat.setSuppressDeclaration(skipDeclaration);
        prettyFormat.setEncoding("UTF-8");

        final StringWriter stringWriter = new StringWriter();
        new XMLWriter(stringWriter, prettyFormat).write(xmlData);

        return stringWriter.toString();
    }

    @NotNull
    public static String prettifyXMLData(@NotNull final DefaultElement xmlData) {
        try {
            return prettifyXMLData(xmlData, 4, false);
        } catch (final IOException e) {
            return "";
        }
    }

    @NotNull
    public static String levelDataAsXML(@NotNull final File ccLocalLevelsDatFile) {
        try {
            final byte[] bytes = Files.readAllBytes(ccLocalLevelsDatFile.toPath());
            final byte[] xorBytes = xorByteArray(bytes, GD_SAVEFILE_XOR_CONSTANT);
            final byte[] bytesDecoded = decodeBase64(new String(xorBytes));
            final String decompressed = decompressZlibBytes(bytesDecoded);
            if (decompressed.endsWith("</plist>")) return decompressed;

            return StringUtils.substringBeforeLast(decompressed, "</plist>") + "</plist>";
        } catch (final IOException | DataFormatException e) {
            throw new LevelDecryptionException(e);
        }
    }

    @NotNull
    public static List<DefaultElement> decryptAllLevelDocuments(@NotNull final File ccLocalLevelsDatFile)  {
        try {
            final String xmlData = levelDataAsXML(ccLocalLevelsDatFile);
            final Document document = DocumentHelper.parseText(xmlData);

            return document.selectNodes("/plist/dict/d/d")
                    .stream()
                    .map(n -> (DefaultElement) n)
                    .toList();
        } catch (final DocumentException e) {
            throw new LevelDecryptionException(e);
        }
    }

    @NotNull
    public static DefaultElement decryptLevelDocument(@NotNull final File ccLocalLevelsDatFile, final int levelIndex) {
        final List<DefaultElement> documents = decryptAllLevelDocuments(ccLocalLevelsDatFile);
        return documents.get(levelIndex);
    }

    @NotNull
    public static List<LazyLevelObject> decryptLevelObjects(@NotNull final String @NotNull [] objectPropertyStrings, final boolean removeFirst) {
        final int offset = removeFirst ? 1 : 0;
        // start at index 1 to skip level settings string

        if (objectPropertyStrings.length == offset) return new ArrayList<>();

        final List<LazyLevelObject> levelObjects = new ArrayList<>(objectPropertyStrings.length - offset);

        for (int i = offset; i < objectPropertyStrings.length; i++) {
            levelObjects.add(new LazyLevelObject(objectPropertyStrings[i]));
        }

        /*final Set<LevelObject> levelObjects = Arrays.stream(objectPropertyStrings)
                .map(s -> PropertyUtil.decodeProperties(s, ","))
                .map(Properties::new)
                .map(LevelObject::parse)
                .collect(Collectors.toSet());*/
        return levelObjects;
    }

    @NotNull
    public static List<LocalLevel> decryptAllLevels(@NotNull final File ccLocalLevelsDatFile) {
        return decryptAllLevelDocuments(ccLocalLevelsDatFile)
                .stream()
                .map(LevelDataDecryption::decryptLevel)
                .toList();
    }

    @NotNull
    public static LocalLevel decryptLevel(@NotNull final File ccLocalLevelsDatFile, final int levelIndex) {
        return decryptLevel(decryptLevelDocument(ccLocalLevelsDatFile, levelIndex));
    }

    @NotNull
    public static LocalLevelProperties decryptLevelProperties(@NotNull final DefaultElement levelDocument) {
        final int nodeCount = levelDocument.nodeCount();
        if (levelDocument.nodeCount() <= -1) throw new LevelDecryptionException("Level document has no data");

        final LocalLevelProperties properties = new LocalLevelProperties();

        for (int i = 0; i < nodeCount && i + 1 < nodeCount; i += 2) {
            final Node keyNode = levelDocument.node(i);
            final Node valueNode = levelDocument.node(i + 1);

            final String key = keyNode.getText();
            final String value = valueNode.getText();
            properties.putProperty(key, value);
        }
        return properties;
    }

    @NotNull
    public static LocalLevel decryptLevel(@NotNull final DefaultElement levelDocument) {
        return decryptLevel(decryptLevelProperties(levelDocument));
    }

    @NotNull
    public static LocalLevel decryptLevel(@NotNull final LocalLevelProperties properties) {
        final String innerLevelString = properties.innerLevelString();
        return new LocalLevel(properties, innerLevelString == null ? new LevelData() : decryptInnerLevelString(innerLevelString));
    }

    @NotNull
    public static LevelData decryptInnerLevelString(@NotNull final String innerLevelString) {
        final String[] objectPropertyStrings = innerLevelString.split(";");
        final String levelSettingsString = objectPropertyStrings[0];
        final List<LazyLevelObject> levelObjects = decryptLevelObjects(objectPropertyStrings, true);

        final LevelSettings settings = new LevelSettings(levelSettingsString);
        return new LevelData(settings, levelObjects);
    }

}
