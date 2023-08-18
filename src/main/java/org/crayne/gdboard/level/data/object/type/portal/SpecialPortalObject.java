package org.crayne.gdboard.level.data.object.type.portal;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
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
        this.specialPropertyChecked = objectProperties.booleanProperty(LevelObjectProperty.SPECIAL_PROPERTY);
    }

    public boolean specialPropertyChecked() {
        return specialPropertyChecked;
    }

    public void specialPropertyChecked(final boolean specialPropertyChecked) {
        this.specialPropertyChecked = specialPropertyChecked;
    }

    @NotNull
    public String toString() {
        return "SpecialPortalObject{" +
                "specialPropertyChecked=" + specialPropertyChecked +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final SpecialPortalObject that = (SpecialPortalObject) o;

        return specialPropertyChecked == that.specialPropertyChecked;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (specialPropertyChecked ? 1 : 0);
        return result;
    }
}
