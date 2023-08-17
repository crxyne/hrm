package org.crayne.gdboard.savefile.property;

import org.crayne.gdboard.level.data.color.ColorHSBModifier;
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

    @NotNull
    public static List<Integer> parseIntegerArray(@Nullable final String s) {
        return s == null ? new ArrayList<>() : Arrays.stream(s.split("\\."))
                .map(PropertyUtil::tryParseNullableInteger)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @NotNull
    public static List<Integer> parseIntegerArray(@Nullable final String s, final int[] defaultValue) {
        return s == null
                ? Arrays.stream(defaultValue).boxed().toList()
                : Arrays.stream(s.split("\\."))

                .map(PropertyUtil::tryParseNullableInteger)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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
            if (i + 1 >= propertySplit.length) break;

            final String value = propertySplit[i + 1];
            properties.put(key, value);
        }
        return properties;
    }


}
