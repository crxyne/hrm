package org.crayne.gdboard.level.data.object.type.decoration;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class AnimatedObject extends LevelObject {
    // for some weird reason that robtop decided, this class only includes 4 of the animated objects
    // in the last page of the orb tab (why?) or atleast i couldnt find any others with the properties 106 and 107
    // the ids of said objects are: 1839, 1840, 1841 and 1842

    private boolean randomizeStart;
    private float animationSpeed;

    public AnimatedObject(final int objectID, final float positionX, final float positionY, final boolean randomizeStart, final float animationSpeed) {
        super(objectID, positionX, positionY);
        this.randomizeStart = randomizeStart;
        this.animationSpeed = animationSpeed;
    }

    public AnimatedObject(@NotNull final LevelObject levelObject, final boolean randomizeStart, final float animationSpeed) {
        super(levelObject);
        this.randomizeStart = randomizeStart;
        this.animationSpeed = animationSpeed;
    }

    public AnimatedObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
    }

    public AnimatedObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public AnimatedObject(@NotNull final Properties objectProperties) {
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
