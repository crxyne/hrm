package org.crayne.hrm.api.savefile.encrypt;

import me.steinborn.libdeflate.CompressionType;
import me.steinborn.libdeflate.LibdeflateCompressor;
import org.apache.commons.codec.binary.Base64;
import org.crayne.hrm.api.level.LevelData;
import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.level.LocalLevelProperties;
import org.crayne.hrm.api.level.data.object.type.LazyLevelObject;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
import org.dom4j.*;
import org.dom4j.tree.DefaultElement;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class LevelDataEncryption {

    private LevelDataEncryption() {}

    @NotNull
    public static String createInnerLevelString(@NotNull final LevelData levelData) {
        final String[] objectPropertyStrings = encryptLevelObjects(new ArrayList<>(levelData.levelObjects()), true);
        objectPropertyStrings[0] = levelData.levelSettings().createLevelSettingsString();
        System.out.println(objectPropertyStrings[0]);
        return String.join(";", objectPropertyStrings) + ";";
    }

    public static void encryptLevel(@NotNull final File ccLocalLevelsDatFile, @NotNull final LocalLevel level) {
        final String innerLevelString = createInnerLevelString(level.data());
        final int updatedObjectCount = level.data().levelObjects().size();
        final LocalLevelProperties properties = level.properties();

        properties.innerLevelString(innerLevelString);
        properties.objectCount(updatedObjectCount);

        encryptLevelProperties(ccLocalLevelsDatFile, properties);
    }

    public static void encryptLevelProperties(@NotNull final File ccLocalLevelsDatFile, @NotNull final LocalLevelProperties properties) {
        try {
            final String xmlData = LevelDataDecryption.levelDataAsXML(ccLocalLevelsDatFile);
            final Document document = DocumentHelper.parseText(xmlData);
            encryptLevelProperties(document, properties);

            saveXMLDataToFile(ccLocalLevelsDatFile, document);
        } catch (final DocumentException e) {
            throw new LevelEncryptionException(e);
        }
    }

    public static void encryptLevelProperties(@NotNull final Document levelDocument, @NotNull final LocalLevelProperties properties) {
        final DefaultElement levels = ((DefaultElement) levelDocument.selectSingleNode("/plist/dict/d"));
        final int startIndex = levels.elements().stream().map(Element::getName).toList().indexOf("d") - 1;
        levels.elements().add(startIndex, properties.encryptLevelProperties());
        levels.elements().add(startIndex, DocumentHelper.createElement("k").addText("k_0"));

        final List<Node> indexKeys = levelDocument.selectNodes("/plist/dict/d/k");

        // skip _isArr key as well as the new k_0
        for (int i = 2; i < indexKeys.size(); i++) {
            final Node indexKey = indexKeys.get(i);
            final int currentIndex = Integer.parseInt(indexKey.getText().substring("k_".length()));

            indexKey.setText("k_" + (currentIndex + 1));
        }
    }

    @NotNull
    public static String @NotNull [] encryptLevelObjects(@NotNull final List<LazyLevelObject> levelObjects, final boolean shiftIndices) {
        final int offset = shiftIndices ? 1 : 0;
        final String[] objectPropertyStrings = new String[levelObjects.size() + offset];

        for (int i = offset; i < levelObjects.size() + offset; i++) {
            objectPropertyStrings[i] = levelObjects.get(i - offset).propertiesString();
        }
        return objectPropertyStrings;
    }

    public static byte @NotNull [] compressZlibBytes(final byte @NotNull [] uncompressedData) {
        final byte[] result = new byte[uncompressedData.length + 32];
        final int size;
        try (final LibdeflateCompressor compressor = new LibdeflateCompressor()) {
            size = compressor.compress(uncompressedData, result, CompressionType.GZIP);
        }
        return Arrays.copyOf(result, size);
    }

    public static byte @NotNull [] encodeBase64(@NotNull final String decoded) {
        return new String(Base64.encodeBase64(decoded.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)
                .replace('+', '-').replace('/', '_').getBytes(StandardCharsets.UTF_8);
    }

    public static byte @NotNull [] encodeBase64(final byte @NotNull [] decodedBytes) {
        return new String(Base64.encodeBase64(decodedBytes), StandardCharsets.UTF_8)
                .replace('+', '-').replace('/', '_').getBytes(StandardCharsets.UTF_8);
    }

    public static void saveXMLDataToFile(@NotNull final File ccLocalLevelsDatFile, @NotNull final Document document) {
        try {
            final byte[] compressedXML = compressZlibBytes(document.asXML().getBytes(StandardCharsets.UTF_8));
            final byte[] bytesEncodedBase64 = encodeBase64(compressedXML);
            final byte[] xorBytes = LevelDataDecryption.xorByteArray(bytesEncodedBase64, LevelDataDecryption.GD_SAVEFILE_XOR_CONSTANT);
            Files.write(ccLocalLevelsDatFile.toPath(), xorBytes);
        } catch (final IOException e) {
            throw new LevelEncryptionException(e);
        }
    }

}
