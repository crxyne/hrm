package org.crayne.gdboard.savefile.decrypt;

import me.steinborn.libdeflate.CompressionType;
import me.steinborn.libdeflate.LibdeflateDecompressor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.crayne.gdboard.level.LocalLevel;
import org.crayne.gdboard.level.LocalLevelProperties;
import org.crayne.gdboard.level.LevelData;
import org.crayne.gdboard.level.data.object.ObjectID;
import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.level.data.settings.LevelSettings;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.PropertyUtil;
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
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.zip.DataFormatException;

@SuppressWarnings("unused")
public class LevelDataDecryption {

    private LevelDataDecryption() {}

    private static final int GD_XOR_CONSTANT = 11;

    private static byte @NotNull [] xorByteArray(final byte @NotNull [] bytes, @SuppressWarnings("SameParameterValue") final int xorValue) {
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
        final byte[] compressedDataHeadless = Arrays.copyOfRange(compressedBytes, 10, compressedBytes.length);
        boolean success = false;
        byte[] output;
        int size = CONST_48_MEGABYTES;

        while (true) {
            output = new byte[size]; // virtual memory is efficient, dont worry about it

            try (final LibdeflateDecompressor decompressor = new LibdeflateDecompressor()) {
                final long result = decompressor.decompressUnknownSize(compressedDataHeadless, output, CompressionType.DEFLATE);
                if (result >= 0) break;

                size *= 2; // double the amount of bytes needed if 48mb for a geometry dash level werent enough
                // (even woodkids inner level string can be uncompressed with the usual 48mb limit just fine)
            }
        }
        return new String(output, StandardCharsets.UTF_8).trim();

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
            final byte[] xorBytes = xorByteArray(bytes, GD_XOR_CONSTANT);
            final byte[] bytesDecoded = decodeBase64(new String(xorBytes));
            final String decompressed = decompressZlibBytes(bytesDecoded);
            if (decompressed.endsWith("</plist>")) return decompressed;

            return StringUtils.substringBeforeLast(decompressed, "</plist>") + "</plist>";
        } catch (final IOException | DataFormatException e) {
            throw new LevelDecryptionException(e);
        }
    }

    @NotNull
    public static LocalLevel decryptLevelData(@NotNull final File ccLocalLevelsDatFile) {
        return decryptLevelData(ccLocalLevelsDatFile, 0);
    }

    @NotNull
    public static List<DefaultElement> decryptAllLevelDocuments(@NotNull final File ccLocalLevelsDatFile)  {
        try {
            final String xmlData = levelDataAsXML(ccLocalLevelsDatFile);
            final Document document = DocumentHelper.parseText(xmlData);
            final Node test = document.selectNodes("/plist/dict/d/d").get(0);

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
    public static List<LevelObject> decryptLevelObjects(@NotNull final String @NotNull [] objectPropertyStrings) {
        if (objectPropertyStrings.length == 1) return new ArrayList<>();

        final List<LevelObject> levelObjects = new ArrayList<>(objectPropertyStrings.length - 1);

        // start at index 1 to skip level settings string
        for (int i = 1; i < objectPropertyStrings.length; i++) {
            final Properties properties = new Properties(PropertyUtil.decodeProperties(objectPropertyStrings[i], ","));
            final LevelObject levelobj = ObjectID.parse(properties);
            if (levelobj.objectID() == 0 || (levelobj.positionX() == 0 && levelobj.positionY() == 0)) continue; // skip invalid objects
            levelObjects.add(levelobj);
        }

        /*final Set<LevelObject> levelObjects = Arrays.stream(objectPropertyStrings)
                .map(s -> PropertyUtil.decodeProperties(s, ","))
                .map(Properties::new)
                .map(LevelObject::parse)
                .collect(Collectors.toSet());*/
        return levelObjects;
    }

    @NotNull
    public static List<LocalLevel> decryptAllLevelData(@NotNull final File ccLocalLevelsDatFile) {
        return decryptAllLevelDocuments(ccLocalLevelsDatFile)
                .stream()
                .map(LevelDataDecryption::decryptLevelData)
                .toList();
    }

    @NotNull
    public static LocalLevel decryptLevelData(@NotNull final File ccLocalLevelsDatFile, final int levelIndex) {
        return decryptLevelData(decryptLevelDocument(ccLocalLevelsDatFile, levelIndex));
    }

    @NotNull
    public static LocalLevel decryptLevelData(@NotNull final DefaultElement levelDocument) {
        final int nodeCount = levelDocument.nodeCount() - 1;
        if (levelDocument.nodeCount() <= -1) throw new LevelDecryptionException("Level document has no data");

        final LocalLevelProperties properties = new LocalLevelProperties();

        for (int i = 0; i < nodeCount && i + 1 < nodeCount; i += 2) {
            final Node keyNode = levelDocument.node(i);
            final Node valueNode;
            valueNode = levelDocument.node(i + 1);

            final String key = keyNode.getText();
            final String value = valueNode.getText();
            properties.putProperty(key, value);
        }
        final String[] objectPropertyStrings = Objects.requireNonNull(properties.innerLevelString()).split(";");
        final String levelSettingsString = objectPropertyStrings[0];
        final List<LevelObject> levelObjects = decryptLevelObjects(objectPropertyStrings);

        final LevelSettings settings = new LevelSettings(levelSettingsString);
        final LevelData levelData = new LevelData(settings, levelObjects);
        return new LocalLevel(properties, levelData);
    }

}
