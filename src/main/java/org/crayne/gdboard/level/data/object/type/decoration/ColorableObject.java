package org.crayne.gdboard.level.data.object.type.decoration;

import org.crayne.gdboard.level.data.color.ColorHSBModifier;
import org.crayne.gdboard.level.data.object.ObjectID;
import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        this.mainColorChannelIndex   = mainColorMutable   ? objectProperties.integerProperty(LevelObjectData.MAIN_COLOR_ID)   : 0;
        this.secondColorChannelIndex = secondColorMutable ? objectProperties.integerProperty(LevelObjectData.SECOND_COLOR_ID) : 0;

        this.mainColorHSBEnabled     = mainColorMutable   && objectProperties.booleanProperty(LevelObjectData.MAIN_COLOR_HSB_CHECKED);
        this.secondColorHSBEnabled   = secondColorMutable && objectProperties.booleanProperty(LevelObjectData.SECOND_COLOR_HSB_CHECKED);
        this.mainColorHSB            = mainColorMutable   ? objectProperties.hsbModifierProperty(LevelObjectData.MAIN_COLOR_HSB)   : ColorHSBModifier.none();
        this.secondColorHSB          = secondColorMutable ? objectProperties.hsbModifierProperty(LevelObjectData.SECOND_COLOR_HSB) : ColorHSBModifier.none();
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

}
