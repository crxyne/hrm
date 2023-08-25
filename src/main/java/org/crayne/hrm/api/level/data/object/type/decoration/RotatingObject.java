package org.crayne.hrm.api.level.data.object.type.decoration;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

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

    @NotNull
    private static final Set<Integer> OBJECT_IDS = Set.of(85, 86, 87, 97, 137, 138, 139, 154, 155, 156,
            180, 181, 182, 183, 184, 185, 186, 187, 188,
            222, 223, 224, 375, 376, 377, 378, 394, 395,
            396, 678, 679, 680, 740, 741, 742, 997, 998,
            999, 1000, 1019, 1020, 1021, 1055, 1056, 1057,
            1058, 1059, 1060, 1061, 1521, 1522, 1523, 1524,
            1525, 1526, 1527, 1528, 1582, 1619, 1620, 1705,
            1706, 1707, 1708, 1709, 1710, 1734, 1735, 1736,
            1752, 1831, 1832, 1833, 1834);

    @NotNull
    public static Set<Integer> objectIDs() {
        return OBJECT_IDS;
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putFloatProperty(LevelObjectProperty.ROTATABLE_SPEED, customRotationSpeed);
        properties.putBooleanProperty(LevelObjectProperty.ROTATABLE_DISABLE, disableRotation);

        return properties;
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
