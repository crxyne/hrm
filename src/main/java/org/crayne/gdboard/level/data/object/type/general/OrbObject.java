package org.crayne.gdboard.level.data.object.type.general;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class OrbObject extends LevelObject {

    private boolean multiActivateEnabled;

    public OrbObject(final int objectID, final float positionX, final float positionY, final boolean multiActivateEnabled) {
        super(objectID, positionX, positionY);
        this.multiActivateEnabled = multiActivateEnabled;
    }

    public OrbObject(@NotNull final LevelObject levelObject, final boolean multiActivateEnabled) {
        super(levelObject);
        this.multiActivateEnabled = multiActivateEnabled;
    }

    public OrbObject(final int objectID, final float positionX, final float positionY) {
        this(objectID, positionX, positionY, false);
    }

    public OrbObject(@NotNull final LevelObject levelObject) {
        this(levelObject, false);
    }

    public OrbObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.multiActivateEnabled = objectProperties.booleanProperty(LevelObjectData.ORB_MULTI_ACTIVATE);
    }

    public boolean multiActivateEnabled() {
        return multiActivateEnabled;
    }

    public void multiActivateEnabled(final boolean multiActivateEnabled) {
        this.multiActivateEnabled = multiActivateEnabled;
    }

}
