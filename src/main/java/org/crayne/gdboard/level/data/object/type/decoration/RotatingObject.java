package org.crayne.gdboard.level.data.object.type.decoration;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class RotatingObject extends ColorableObject {

    private float customRotationSpeed;
    private boolean disableRotation;

    public RotatingObject(final int objectID, final float positionX, final float positionY, final float customRotationSpeed) {
        super(objectID, positionX, positionY);
        this.customRotationSpeed = customRotationSpeed;
    }

    public RotatingObject(@NotNull final LevelObject levelObject, final float customRotationSpeed) {
        super(levelObject);
        this.customRotationSpeed = customRotationSpeed;
    }

    public RotatingObject(final int objectID, final float positionX, final float positionY, final boolean disableRotation) {
        super(objectID, positionX, positionY);
        this.disableRotation = disableRotation;
    }

    public RotatingObject(@NotNull final LevelObject levelObject, final boolean disableRotation) {
        super(levelObject);
        this.disableRotation = disableRotation;
    }

    public RotatingObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public RotatingObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public RotatingObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.customRotationSpeed = objectProperties.floatProperty(LevelObjectProperty.ROTATABLE_SPEED);
        this.disableRotation = objectProperties.booleanProperty(LevelObjectProperty.ROTATABLE_DISABLE);
    }

    public float customRotationSpeed() {
        return customRotationSpeed;
    }

    public void customRotationSpeed(final float customRotationSpeed) {
        this.customRotationSpeed = customRotationSpeed;
        this.disableRotation = false;
    }

    public boolean disableRotation() {
        return disableRotation;
    }

    public void disableRotation(final boolean disableRotation) {
        this.disableRotation = disableRotation;
        if (disableRotation) this.customRotationSpeed = 0;
    }

    @NotNull
    public String toString() {
        return "RotatingObject{" +
                "customRotationSpeed=" + customRotationSpeed +
                ", disableRotation=" + disableRotation +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final RotatingObject that = (RotatingObject) o;

        if (Float.compare(that.customRotationSpeed, customRotationSpeed) != 0) return false;
        return disableRotation == that.disableRotation;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customRotationSpeed != 0.0f ? Float.floatToIntBits(customRotationSpeed) : 0);
        result = 31 * result + (disableRotation ? 1 : 0);
        return result;
    }
}
