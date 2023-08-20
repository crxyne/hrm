package org.crayne.hrm.api.level.data.object.type.decoration;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.crayne.hrm.api.level.data.color.ColorHSBModifier;
import org.crayne.hrm.api.level.data.object.ObjectID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public class ColorableObject extends LevelObject {

    private int mainColorChannelIndex, secondColorChannelIndex;
    private boolean mainColorHSBEnabled, secondColorHSBEnabled;

    @Nullable
    private ColorHSBModifier mainColorHSB, secondColorHSB;
    private final boolean mainColorMutable, secondColorMutable;

    public ColorableObject(final int objectID, final float positionX, final float positionY, final int mainColorChannelIndex,
                           final int secondColorChannelIndex, @Nullable final ColorHSBModifier mainColorHSB,
                           @Nullable final ColorHSBModifier secondColorHSB) {
        super(objectID, positionX, positionY);
        final boolean bothMutable = ObjectID.isBaseAndDetailColorObject(objectID);
        this.mainColorMutable = ObjectID.isBaseColorObject(objectID) || bothMutable;
        this.secondColorMutable = ObjectID.isDetailColorObject(objectID) || bothMutable;

        this.mainColorChannelIndex   = mainColorMutable   ? mainColorChannelIndex   : 0;
        this.secondColorChannelIndex = secondColorMutable ? secondColorChannelIndex : 0;
        this.mainColorHSBEnabled     = mainColorMutable   && mainColorHSB   != null;
        this.secondColorHSBEnabled   = secondColorMutable && secondColorHSB != null;
        this.mainColorHSB            = mainColorMutable   ? mainColorHSB   : ColorHSBModifier.none();
        this.secondColorHSB          = secondColorMutable ? secondColorHSB : ColorHSBModifier.none();
    }

    public ColorableObject(@NotNull final LevelObject levelObject, final int mainColorChannelIndex, final int secondColorChannelIndex) {
        super(levelObject);
        final boolean bothMutable = ObjectID.isBaseAndDetailColorObject(objectID());
        this.mainColorMutable = ObjectID.isBaseColorObject(objectID()) || bothMutable;
        this.secondColorMutable = ObjectID.isDetailColorObject(objectID()) || bothMutable;

        this.mainColorChannelIndex   = mainColorMutable   ? mainColorChannelIndex   : 0;
        this.secondColorChannelIndex = secondColorMutable ? secondColorChannelIndex : 0;
    }


    public ColorableObject(final int objectID, final float positionX, final float positionY) {
        super(objectID, positionX, positionY);
        final boolean bothMutable = ObjectID.isBaseAndDetailColorObject(objectID);
        this.mainColorMutable = ObjectID.isBaseColorObject(objectID) || bothMutable;
        this.secondColorMutable = ObjectID.isDetailColorObject(objectID) || bothMutable;
    }

    public ColorableObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
        final boolean bothMutable = ObjectID.isBaseAndDetailColorObject(objectID());
        this.mainColorMutable = ObjectID.isBaseColorObject(objectID()) || bothMutable;
        this.secondColorMutable = ObjectID.isDetailColorObject(objectID()) || bothMutable;
    }

    public ColorableObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        final boolean bothMutable = ObjectID.isBaseAndDetailColorObject(objectID());
        this.mainColorMutable = ObjectID.isBaseColorObject(objectID()) || bothMutable;
        this.secondColorMutable = ObjectID.isDetailColorObject(objectID()) || bothMutable;

        this.mainColorChannelIndex   = objectProperties.integerProperty(LevelObjectProperty.MAIN_COLOR_ID);
        this.secondColorChannelIndex = objectProperties.integerProperty(LevelObjectProperty.SECOND_COLOR_ID);

        this.mainColorHSBEnabled     = objectProperties.booleanProperty(LevelObjectProperty.MAIN_COLOR_HSB_CHECKED);
        this.secondColorHSBEnabled   = objectProperties.booleanProperty(LevelObjectProperty.SECOND_COLOR_HSB_CHECKED);
        this.mainColorHSB            = objectProperties.hsbModifierProperty(LevelObjectProperty.MAIN_COLOR_HSB);
        this.secondColorHSB          = objectProperties.hsbModifierProperty(LevelObjectProperty.SECOND_COLOR_HSB);
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = super.createProperties();
        properties.putIntProperty(LevelObjectProperty.MAIN_COLOR_ID, mainColorChannelIndex);
        properties.putIntProperty(LevelObjectProperty.SECOND_COLOR_ID, secondColorChannelIndex);
        properties.putBooleanProperty(LevelObjectProperty.MAIN_COLOR_HSB_CHECKED, mainColorHSBEnabled);
        properties.putBooleanProperty(LevelObjectProperty.SECOND_COLOR_HSB_CHECKED, secondColorHSBEnabled);
        properties.putHSBModifierProperty(LevelObjectProperty.MAIN_COLOR_HSB, mainColorHSB);
        properties.putHSBModifierProperty(LevelObjectProperty.SECOND_COLOR_HSB, secondColorHSB);

        return properties;
    }

    public int mainColorChannelIndex() {
        return mainColorChannelIndex;
    }

    public void mainColorChannelIndex(final int mainColorChannelIndex) {
        if (!mainColorMutable) throw new UnsupportedOperationException();
        this.mainColorChannelIndex = mainColorChannelIndex;
    }

    public int secondColorChannelIndex() {
        return secondColorChannelIndex;
    }

    public void secondColorChannelIndex(final int secondColorChannelIndex) {
        if (!secondColorMutable) throw new UnsupportedOperationException();
        this.secondColorChannelIndex = secondColorChannelIndex;
    }

    public boolean mainColorHSBEnabled() {
        return mainColorHSBEnabled;
    }

    public void mainColorHSBEnabled(final boolean mainColorHSBEnabled) {
        if (!mainColorMutable) throw new UnsupportedOperationException();
        this.mainColorHSBEnabled = mainColorHSBEnabled;
    }

    public boolean secondColorHSBEnabled() {
        return secondColorHSBEnabled;
    }

    public void secondColorHSBEnabled(final boolean secondColorHSBEnabled) {
        if (!secondColorMutable) throw new UnsupportedOperationException();
        this.secondColorHSBEnabled = secondColorHSBEnabled;
    }

    @NotNull
    public Optional<ColorHSBModifier> mainColorHSB() {
        return Optional.ofNullable(mainColorHSB);
    }

    public void mainColorHSB(@Nullable final ColorHSBModifier mainColorHSB) {
        if (!mainColorMutable) throw new UnsupportedOperationException();
        this.mainColorHSB = mainColorHSB;
    }

    @NotNull
    public Optional<ColorHSBModifier> secondColorHSB() {
        return Optional.ofNullable(secondColorHSB);
    }

    public void secondColorHSB(@Nullable final ColorHSBModifier secondColorHSB) {
        if (!secondColorMutable) throw new UnsupportedOperationException();
        this.secondColorHSB = secondColorHSB;
    }

    @NotNull
    public String toString() {
        return "ColorableObject{" +
                "mainColorChannelIndex=" + mainColorChannelIndex +
                ", secondColorChannelIndex=" + secondColorChannelIndex +
                ", mainColorHSBEnabled=" + mainColorHSBEnabled +
                ", secondColorHSBEnabled=" + secondColorHSBEnabled +
                ", mainColorHSB=" + mainColorHSB +
                ", secondColorHSB=" + secondColorHSB +
                ", mainColorMutable=" + mainColorMutable +
                ", secondColorMutable=" + secondColorMutable +
                "} " + super.toString();
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final ColorableObject that = (ColorableObject) o;

        if (mainColorChannelIndex != that.mainColorChannelIndex) return false;
        if (secondColorChannelIndex != that.secondColorChannelIndex) return false;
        if (mainColorHSBEnabled != that.mainColorHSBEnabled) return false;
        if (secondColorHSBEnabled != that.secondColorHSBEnabled) return false;
        if (mainColorMutable != that.mainColorMutable) return false;
        if (secondColorMutable != that.secondColorMutable) return false;
        if (!Objects.equals(mainColorHSB, that.mainColorHSB)) return false;
        return Objects.equals(secondColorHSB, that.secondColorHSB);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mainColorChannelIndex;
        result = 31 * result + secondColorChannelIndex;
        result = 31 * result + (mainColorHSBEnabled ? 1 : 0);
        result = 31 * result + (secondColorHSBEnabled ? 1 : 0);
        result = 31 * result + (mainColorHSB != null ? mainColorHSB.hashCode() : 0);
        result = 31 * result + (secondColorHSB != null ? secondColorHSB.hashCode() : 0);
        result = 31 * result + (mainColorMutable ? 1 : 0);
        result = 31 * result + (secondColorMutable ? 1 : 0);
        return result;
    }
}
