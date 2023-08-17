package org.crayne.gdboard.level.data.object.type.decoration;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

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
        this.randomizeStart = objectProperties.booleanProperty(LevelObjectData.ANIMATION_RANDOMIZE_START);
        this.animationSpeed = objectProperties.floatProperty(LevelObjectData.ANIMATION_SPEED);
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

}
