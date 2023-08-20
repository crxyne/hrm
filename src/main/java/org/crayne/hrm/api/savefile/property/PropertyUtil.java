package org.crayne.hrm.api.savefile.property;

import org.crayne.hrm.api.level.data.color.ColorHSBModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class PropertyUtil {

    private PropertyUtil() {}

    public static float tryParseFloat(@NotNull final String ofString, final float defaultValue) {
        try {
            return Float.parseFloat(ofString);
        } catch (final NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int tryParseInt(@NotNull final String ofString, final int defaultValue) {
        try {
            return Integer.parseInt(ofString);
        } catch (final NumberFormatException e) {
            return defaultValue;
        }
    }

    @Nullable
    public static Integer tryParseNullableInteger(@NotNull final String ofString) {
        try {
            return Integer.parseInt(ofString);
        } catch (final NumberFormatException e) {
            return null;
        }
    }

    public static List<String> tokenizeFast(@NotNull final String s, final char delimiter) {
        final List<String> stringSplit = new ArrayList<>();
        if (s.isEmpty()) return stringSplit;

        int pos = 0, end;
        while ((end = s.indexOf(delimiter, pos)) >= 0) {
            stringSplit.add(s.substring(pos, end));
            pos = end + 1;
        }
        stringSplit.add(s.substring(pos));
        return stringSplit;
    }

    @NotNull
    public static List<Integer> parseIntegerArray(@Nullable final String s, @NotNull final List<Integer> defaultValue) {
        final List<Integer> intSplit = new ArrayList<>();
        if (s == null || s.isEmpty()) return defaultValue;

        int pos = 0, end;
        while ((end = s.indexOf('.', pos)) >= 0) {
            intSplit.add(Integer.parseInt(s.substring(pos, end)));
            pos = end + 1;
        }
        intSplit.add(Integer.parseInt(s.substring(pos)));
        return intSplit;
    }

    public static int parseIntValue(@Nullable final String s, final int defaultValue) {
        return s == null ? defaultValue : tryParseInt(s, defaultValue);
    }

    @Nullable
    public static Integer parseNullableIntegerValue(@Nullable final String s) {
        return s == null ? null : tryParseNullableInteger(s);
    }

    public static float parseFloatValue(@Nullable final String s, final float defaultValue) {
        return s == null ? defaultValue : tryParseFloat(s, defaultValue);
    }

    public static boolean parseBooleanValue(@Nullable final String s, final boolean defaultValue) {
        return s == null ? defaultValue : s.equals("1");
    }

    public static boolean parseBooleanValue(@Nullable final String s) {
        return parseBooleanValue(s, false);
    }

    @Nullable
    public static ColorHSBModifier parseHSBValue(@Nullable final String s) {
        return Optional.ofNullable(s).map(ColorHSBModifier::new).orElse(null);
    }

    @NotNull
    public static Map<String, String> decodeProperties(@NotNull final String encodedString, @NotNull final String delimiter) {
        final String[] propertySplit = encodedString.split(delimiter);
        final Map<String, String> properties = new HashMap<>();

        for (int i = 0; i < propertySplit.length; i += 2) {
            final String key = propertySplit[i];
            if (i + 1 >= propertySplit.length) return new HashMap<>();

            final String value = propertySplit[i + 1];
            properties.put(key, value);
        }
        return properties;
    }

    @NotNull
    public static String encodeProperties(@NotNull final Map<String, String> properties, @NotNull final String delimiter) {
        return properties.keySet().stream().filter(Objects::nonNull).map(key -> key + delimiter + properties.get(key)).collect(Collectors.joining(delimiter));
    }


}
