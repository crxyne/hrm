package org.crayne.gdboard.level.data.object.type.general;

import org.crayne.gdboard.level.data.color.ColorHSBModifier;
import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.jetbrains.annotations.Nullable;

public class ColorableObject extends LevelObject {

    private int mainColorChannelIndex, secondColorChannelIndex; // 21, 22
    private boolean mainColorHSBEnabled, secondColorHSBEnabled; // 41, 42

    @Nullable
    private ColorHSBModifier mainColorHSB, secondColorHSB; // 43, 44

    public ColorableObject(final int mainColorChannelIndex, final int secondColorChannelIndex, final boolean mainColorHSBEnabled, final boolean secondColorHSBEnabled, @Nullable final ColorHSBModifier mainColorHSB, @Nullable final ColorHSBModifier secondColorHSB) {
        this.mainColorChannelIndex = mainColorChannelIndex;
        this.secondColorChannelIndex = secondColorChannelIndex;
        this.mainColorHSBEnabled = mainColorHSBEnabled;
        this.secondColorHSBEnabled = secondColorHSBEnabled;
        this.mainColorHSB = mainColorHSB;
        this.secondColorHSB = secondColorHSB;
    }

    public ColorableObject(final int mainColorChannelIndex, final int secondColorChannelIndex, @Nullable final ColorHSBModifier mainColorHSB, @Nullable final ColorHSBModifier secondColorHSB) {
        this.mainColorChannelIndex = mainColorChannelIndex;
        this.secondColorChannelIndex = secondColorChannelIndex;
        this.mainColorHSBEnabled = mainColorHSB != null;
        this.secondColorHSBEnabled = secondColorHSB != null;
        this.mainColorHSB = mainColorHSB;
        this.secondColorHSB = secondColorHSB;
    }

    public ColorableObject(final int mainColorChannelIndex, final int secondColorChannelIndex) {
        this.mainColorChannelIndex = mainColorChannelIndex;
        this.secondColorChannelIndex = secondColorChannelIndex;
    }

    public int mainColorChannelIndex() {
        return mainColorChannelIndex;
    }

    public void mainColorChannelIndex(final int mainColorChannelIndex) {
        this.mainColorChannelIndex = mainColorChannelIndex;
    }

    public int secondColorChannelIndex() {
        return secondColorChannelIndex;
    }

    public void secondColorChannelIndex(final int secondColorChannelIndex) {
        this.secondColorChannelIndex = secondColorChannelIndex;
    }

    public boolean mainColorHSBEnabled() {
        return mainColorHSBEnabled;
    }

    public void mainColorHSBEnabled(final boolean mainColorHSBEnabled) {
        this.mainColorHSBEnabled = mainColorHSBEnabled;
    }

    public boolean secondColorHSBEnabled() {
        return secondColorHSBEnabled;
    }

    public void secondColorHSBEnabled(final boolean secondColorHSBEnabled) {
        this.secondColorHSBEnabled = secondColorHSBEnabled;
    }

    public ColorHSBModifier mainColorHSB() {
        return mainColorHSB;
    }

    public void mainColorHSB(final ColorHSBModifier mainColorHSB) {
        this.mainColorHSB = mainColorHSB;
    }

    public ColorHSBModifier secondColorHSB() {
        return secondColorHSB;
    }

    public void secondColorHSB(final ColorHSBModifier secondColorHSB) {
        this.secondColorHSB = secondColorHSB;
    }

}
