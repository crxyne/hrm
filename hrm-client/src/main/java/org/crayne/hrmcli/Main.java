package org.crayne.hrmcli;

import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
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
        final LocalLevel woodkid = LevelDataDecryption.decryptLevel(new File("CCLocalLevels.dat"), 0);
    }

}