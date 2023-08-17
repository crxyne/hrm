package org.crayne.gdboard.savefile.decrypt;

import org.apache.commons.codec.binary.Base64;
import org.crayne.gdboard.level.LocalLevel;
import org.crayne.gdboard.level.LocalLevelProperties;
import org.crayne.gdboard.level.data.LevelData;
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
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

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

    @NotNull
    public static String decompressZlibBytes(final byte @NotNull [] compressedBytes) throws DataFormatException {
        final byte[] compressedDataHeadless = Arrays.copyOfRange(compressedBytes, 10, compressedBytes.length);

        final Inflater decompress = new Inflater(true);
        decompress.setInput(compressedDataHeadless, 0, compressedDataHeadless.length);

        final byte[] buffer = new byte[5];
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (!decompress.finished()) outputStream.write(buffer, 0, decompress.inflate(buffer));
        decompress.end();

        return outputStream.toString(StandardCharsets.UTF_8);
    }

    public static byte @NotNull [] decodeBase64(@NotNull final String encoded) {
        return Base64.decodeBase64(encoded.replace('-', '+').replace('_', '/').trim());
    }

    @NotNull
    public static String prettifyXMLData(@NotNull final Document xmlData, final int indent, final boolean skipDeclaration) throws IOException {
        final OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        prettyFormat.setIndentSize(indent);
        prettyFormat.setSuppressDeclaration(skipDeclaration);
        prettyFormat.setEncoding("UTF-8");

        final StringWriter stringWriter = new StringWriter();
        new XMLWriter(stringWriter, prettyFormat).write(xmlData);

        return stringWriter.toString();
    }

    @NotNull
    public static String levelDataAsXML(@NotNull final File ccLocalLevelsDatFile) throws DataFormatException, IOException {
        final byte[] bytes = Files.readAllBytes(ccLocalLevelsDatFile.toPath());
        final byte[] xorBytes = xorByteArray(bytes, GD_XOR_CONSTANT);
        final byte[] bytesDecoded = decodeBase64(new String(xorBytes));
        return decompressZlibBytes(bytesDecoded);
    }


    @NotNull
    public static Optional<LocalLevel> decryptLevelData(@NotNull final File ccLocalLevelsDatFile) throws DataFormatException, IOException, DocumentException {
        return decryptLevelData(ccLocalLevelsDatFile, 0);
    }

    @NotNull
    public static Optional<LocalLevel> decryptLevelData(@NotNull final File ccLocalLevelsDatFile, final int levelIndex) throws DataFormatException, IOException, DocumentException {
        final String xmlData = levelDataAsXML(ccLocalLevelsDatFile);
        final Document document = DocumentHelper.parseText(xmlData);
        final List<Node> nodes = document.selectNodes("/plist/dict/d/d");
        final Document levelDocument = DocumentHelper.parseText(nodes.get(levelIndex).asXML());
        final List<Node> levelPropertiesNodes = new ArrayList<>(levelDocument.selectNodes("//*"));

        if (levelPropertiesNodes.isEmpty()) return Optional.empty();
        levelPropertiesNodes.remove(levelPropertiesNodes.size() - 1);

        final LocalLevelProperties properties = new LocalLevelProperties();

        for (int i = 1; i < levelPropertiesNodes.size(); i += 2) {
            final Node keyNode = levelPropertiesNodes.get(i);
            final Node valueNode;
            if (keyNode.getName().equals("d") || i + 1 >= levelPropertiesNodes.size()
                    || (valueNode = levelPropertiesNodes.get(i + 1)).getName().equals("d")) break;

            final String key = keyNode.getText();
            final String value = valueNode.getText();
            properties.putProperty(key, value);
        }
        final String[] objectPropertyStrings = Objects.requireNonNull(properties.innerLevelString()).split(";");
        final String levelSettingsString = objectPropertyStrings[0];

        final Set<LevelObject> levelObjects = Arrays.stream(objectPropertyStrings)
                .map(s -> PropertyUtil.decodeProperties(s, ","))
                .map(Properties::new)
                .map(LevelObject::parse)
                .filter(obj -> obj.objectID() != 0)
                .collect(Collectors.toSet());

        final LevelSettings settings = new LevelSettings(levelSettingsString);
        final LevelData levelData = new LevelData(settings, levelObjects);
        final LocalLevel level = new LocalLevel(properties, levelData);

        return Optional.of(level);
    }

}
