package org.crayne.gdboard;

import org.crayne.gdboard.level.LocalLevel;
import org.crayne.gdboard.savefile.decrypt.LevelDataDecryption;
import org.dom4j.DocumentException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.zip.DataFormatException;

public class Main {

    // TODO fix custom song id reading (currently always 0 no matter what)
    public static void main(@NotNull final String... args) throws IOException, DataFormatException, DocumentException {
        final long start = System.currentTimeMillis();
        final Optional<LocalLevel> levelData = LevelDataDecryption.decryptLevelData(new File("CCLocalLevels.dat"), 0);
        if (levelData.isEmpty()) {
            System.out.println("could not read level data");
            return;
        }
        final long end = System.currentTimeMillis();
        final File writeTo = new File("levelDataClearText.txt");
        Files.writeString(writeTo.toPath(), levelData.get().toString());
        System.out.println("successfully read level: " + levelData.get().properties().levelName() + " in " + (end - start) + " ms");
    }


}