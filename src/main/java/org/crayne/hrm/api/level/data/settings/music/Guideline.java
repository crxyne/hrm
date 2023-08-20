package org.crayne.hrm.api.level.data.settings.music;

import org.crayne.hrm.api.savefile.property.PropertyUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
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
        final Map<String, String> timestampWithTypeMap = PropertyUtil.decodeProperties(guidelineString, "~");
        final Set<Guideline> guidelines = new HashSet<>();

        for (final String timestampString : timestampWithTypeMap.keySet()) {
            final float timestamp = PropertyUtil.parseFloatValue(timestampString, -1);
            if (timestamp == -1) continue;

            final Type type = Type.of(PropertyUtil.parseFloatValue(timestampWithTypeMap.get(timestampString), 0));
            guidelines.add(new Guideline(type, timestamp));
        }
        return guidelines;
    }

    @NotNull
    public static String toGuidelineString(@NotNull final Set<Guideline> guidelines) {
        return guidelines.stream().map(g -> g.timestamp + "~" + g.type.id()).collect(Collectors.joining("~"));
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

        ORANGE(0.8f),
        YELLOW(0.9f),
        GREEN(1.0f),
        TRANSPARENT(0.7f);

        private final float id;

        Type(final float id) {
            this.id = id;
        }

        public float id() {
            return id;
        }

        @NotNull
        public static Type of(final float type) {
            if (type == 0.8 || type == 0) return ORANGE;
            if (type == 0.9) return YELLOW;
            if (type == 1.0) return GREEN;

            if (type < 0.8) return TRANSPARENT;
            return ORANGE;
        }



    }

    @NotNull
    public String toString() {
        return "Guideline{" +
                "timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Guideline guideline = (Guideline) o;

        if (Float.compare(guideline.timestamp, timestamp) != 0) return false;
        return type == guideline.type;
    }

    public int hashCode() {
        int result = (timestamp != 0.0f ? Float.floatToIntBits(timestamp) : 0);
        result = 31 * result + type.hashCode();
        return result;
    }
}
