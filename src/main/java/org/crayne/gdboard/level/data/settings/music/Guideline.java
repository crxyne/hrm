package org.crayne.gdboard.level.data.settings;

import org.crayne.gdboard.decrypt.PropertyDecodeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Guideline {

    private float timestamp;

    @NotNull
    private Type type;

    public Guideline(@NotNull final Type type, final float timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    @NotNull
    public static Set<Guideline> ofGuidelineString(@NotNull final String guidelineString) {
        final Map<String, String> timestampWithTypeMap = PropertyDecodeUtil.decodeProperties(guidelineString, "~");
        final Set<Guideline> guidelines = new HashSet<>();

        for (final String timestampString : timestampWithTypeMap.keySet()) {
            final float timestamp = PropertyDecodeUtil.parseFloatValue(timestampString, -1);
            if (timestamp == -1) continue;

            final Type type = Type.ofID(PropertyDecodeUtil.parseFloatValue(timestampWithTypeMap.get(timestampString), 0));
            guidelines.add(new Guideline(type, timestamp));
        }
        return guidelines;
    }

    public float timestamp() {
        return timestamp;
    }

    public void timestamp(final float timestamp) {
        this.timestamp = timestamp;
    }

    @NotNull
    public Type type() {
        return type;
    }

    public void type(@NotNull final Type type) {
        this.type = type;
    }

    public enum Type {

        ORANGE,
        YELLOW,
        GREEN,
        TRANSPARENT;

        @NotNull
        public static Type ofID(final float type) {
            if (type == 0.8 || type == 0) return ORANGE;
            if (type == 0.9) return YELLOW;
            if (type == 1.0) return GREEN;

            if (type < 0.8) return TRANSPARENT;
            return ORANGE;
        }

    }

}
