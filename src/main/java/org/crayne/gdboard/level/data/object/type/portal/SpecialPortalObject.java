package org.crayne.gdboard.level.data.object.type.portal;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class SpecialPortalObject extends LevelObject {

    private boolean specialPropertyChecked;

    public SpecialPortalObject(final int objectID, final float positionX, final float positionY, final boolean specialPropertyChecked) {
        super(objectID, positionX, positionY);
        this.specialPropertyChecked = specialPropertyChecked;
    }

    public SpecialPortalObject(@NotNull final LevelObject levelObject, final boolean specialPropertyChecked) {
        super(levelObject);
        this.specialPropertyChecked = specialPropertyChecked;
    }

    public SpecialPortalObject(final int objectID, final float positionX, final float positionY) {
        this(objectID, positionX, positionY, false);
    }

    public SpecialPortalObject(@NotNull final LevelObject levelObject) {
        this(levelObject, false);
    }

    public SpecialPortalObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.specialPropertyChecked = objectProperties.booleanProperty(LevelObjectData.SPECIAL_PROPERTY);
    }

    public boolean specialPropertyChecked() {
        return specialPropertyChecked;
    }

    public void specialPropertyChecked(final boolean specialPropertyChecked) {
        this.specialPropertyChecked = specialPropertyChecked;
    }

}
