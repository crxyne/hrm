package org.crayne.gdboard.level.data.object.type.decoration;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
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
        this.customRotationSpeed = objectProperties.floatProperty(LevelObjectData.ROTATABLE_SPEED);
        this.disableRotation = objectProperties.booleanProperty(LevelObjectData.ROTATABLE_DISABLE);
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

}
