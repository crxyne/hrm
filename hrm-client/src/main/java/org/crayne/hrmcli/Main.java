package org.crayne.hrmcli;

import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
import org.dom4j.tree.DefaultElement;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    @NotNull
    private static final Pattern ARG_SPLIT_REGEX = Pattern.compile("\"(?:[^\"\\\\]|\\\\.)*\"|\\S+");

    @NotNull
    private static List<String> splitArguments(@NotNull final String originalString) {
        final Matcher matcher = ARG_SPLIT_REGEX.matcher(originalString);
        final List<String> result = new ArrayList<>();
        while (matcher.find()) {
            final String withQuotes = matcher.group(0).replace("\\\"", "\"");
            if (withQuotes.startsWith("\"") && withQuotes.endsWith("\"")) {
                result.add(withQuotes.substring(1, withQuotes.length() - 1));
                continue;
            }
            result.add(withQuotes);
        }
        return result;
    }

    public static void main(@NotNull final String... args) {
        final List<DefaultElement> levels = LevelDataDecryption.decryptAllLevelDocuments(new File("CCLocalLevels.dat"));
        final long start = System.currentTimeMillis();
        final LocalLevel woodkid = LevelDataDecryption.decryptLevel(levels.get(0));
        final long end = System.currentTimeMillis();
        System.out.println((end - start));

        final long start2 = System.currentTimeMillis();
        final LocalLevel woodkid2 = LevelDataDecryption.decryptLevel(levels.get(0));
        final long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2));

        final long start3 = System.currentTimeMillis();
        final LocalLevel woodkid3 = LevelDataDecryption.decryptLevel(levels.get(0));
        final long end3 = System.currentTimeMillis();
        System.out.println((end3 - start3));

        final long start4 = System.currentTimeMillis();
        final LocalLevel woodkid4 = LevelDataDecryption.decryptLevel(levels.get(0));
        final long end4 = System.currentTimeMillis();
        System.out.println((end4 - start4));


    }

}