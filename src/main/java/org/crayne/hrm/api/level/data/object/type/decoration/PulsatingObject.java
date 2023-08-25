package org.crayne.hrm.api.level.data.object.type.decoration;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("unused")
public class PulsatingObject extends ColorableObject {

    private boolean randomizeStart;
    private float animationSpeed;

    public PulsatingObject(final int objectID, final float positionX, final float positionY, final boolean randomizeStart, final float animationSpeed) {
        super(objectID, positionX, positionY);
        this.randomizeStart = randomizeStart;
        this.animationSpeed = animationSpeed;
    }

    public PulsatingObject(@NotNull final LevelObject levelObject, final boolean randomizeStart, final float animationSpeed) {
        super(levelObject);
        this.randomizeStart = randomizeStart;
        this.animationSpeed = animationSpeed;
    }

    public PulsatingObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public PulsatingObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public PulsatingObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.randomizeStart = objectProperties.booleanProperty(LevelObjectProperty.ANIMATION_RANDOMIZE_START);
        this.animationSpeed = objectProperties.floatProperty(LevelObjectProperty.ANIMATION_SPEED);
    }

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(1839, 1840, 1841, 1842);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putBooleanProperty(LevelObjectProperty.ANIMATION_RANDOMIZE_START, randomizeStart);
        properties.putFloatProperty(LevelObjectProperty.ANIMATION_SPEED, animationSpeed);

        return properties;
    }

    public boolean randomizeStart() {
        return randomizeStart;
    }

    public void randomizeStart(final boolean randomizeStart) {
        this.randomizeStart = randomizeStart;
    }

    public float animationSpeed() {
        return animationSpeed;
    }

    public void animationSpeed(final float animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    @NotNull
    public String toString() {
        return "PulsatingObject{" +
                "randomizeStart=" + randomizeStart +
                ", animationSpeed=" + animationSpeed +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final PulsatingObject that = (PulsatingObject) o;

        if (randomizeStart != that.randomizeStart) return false;
        return Float.compare(that.animationSpeed, animationSpeed) == 0;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (randomizeStart ? 1 : 0);
        result = 31 * result + (animationSpeed != 0.0f ? Float.floatToIntBits(animationSpeed) : 0);
        return result;
    }
}
