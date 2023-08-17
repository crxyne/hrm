package org.crayne.gdboard.level.data.object.type.trigger.color;

import org.crayne.gdboard.level.data.color.ColorHSBModifier;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("unused")
public class PulseTrigger extends Trigger {

    private int redComponent, greenComponent, blueComponent; // 7, 8, 9
    private boolean blending; // 17
    private float opacity; // 35

    @Nullable
    private ColorHSBModifier copiedColorHSBModifier; // 49
    private int copiedColorChannelIndex; // 50
    private boolean copyOpacity; // 60

    private float pulseFadeIn, pulseHold, pulseFadeOut; // 45, 46, 47

    @NotNull
    private Mode pulseMode; // 48

    @NotNull
    private Target pulseTargetType; // 52

    private boolean mainOnly, detailOnly; // 65, 66
    private boolean exclusive; // 86

    private int targetID; // 51

    public enum Mode {
        COLOR(0), HSB(1);

        private final int id;

        Mode(final int id) {
            this.id = id;
        }

        @NotNull
        public static Mode of(final int id) {
            return id == 1 ? HSB : COLOR;
        }

        public int id() {
            return id;
        }

    }

    public enum Target {
        GROUP(0), CHANNEL(1);

        private final int id;

        Target(final int id) {
            this.id = id;
        }

        @NotNull
        public static Target of(final int id) {
            return id == 1 ? CHANNEL : GROUP;
        }

        public int id() {
            return id;
        }

    }

    public PulseTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int redComponent, final int greenComponent, final int blueComponent,
                        final boolean blending, final float opacity, final float pulseFadeIn, final float pulseHold,
                        final float pulseFadeOut, @NotNull final Target pulseTargetType, final boolean mainOnly, final boolean detailOnly,
                        final boolean exclusive, final int targetID) {
        super(spawnTriggered, multiTriggered);
        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
        this.blending = blending;
        this.opacity = opacity;
        this.pulseFadeIn = pulseFadeIn;
        this.pulseHold = pulseHold;
        this.pulseFadeOut = pulseFadeOut;
        this.pulseMode = Mode.COLOR;
        this.pulseTargetType = pulseTargetType;
        this.mainOnly = mainOnly;
        this.detailOnly = !mainOnly && detailOnly;
        this.exclusive = exclusive;
        this.targetID = targetID;
    }

    public PulseTrigger(final boolean touchTriggered, final int redComponent, final int greenComponent, final int blueComponent,
                        final boolean blending, final float opacity, final float pulseFadeIn,
                        final float pulseHold, final float pulseFadeOut, @NotNull final Target pulseTargetType, final boolean mainOnly,
                        final boolean detailOnly, final boolean exclusive, final int targetID) {
        super(touchTriggered);
        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
        this.blending = blending;
        this.opacity = opacity;
        this.pulseFadeIn = pulseFadeIn;
        this.pulseHold = pulseHold;
        this.pulseFadeOut = pulseFadeOut;
        this.pulseMode = Mode.COLOR;
        this.pulseTargetType = pulseTargetType;
        this.mainOnly = mainOnly;
        this.detailOnly = !mainOnly && detailOnly;
        this.exclusive = exclusive;
        this.targetID = targetID;
    }

    public PulseTrigger(final int redComponent, final int greenComponent, final int blueComponent, final boolean blending,
                        final float opacity, final float pulseFadeIn, final float pulseHold, final float pulseFadeOut,
                        @NotNull final Target pulseTargetType, final boolean mainOnly, final boolean detailOnly, final boolean exclusive, final int targetID) {
        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
        this.blending = blending;
        this.opacity = opacity;
        this.pulseFadeIn = pulseFadeIn;
        this.pulseHold = pulseHold;
        this.pulseFadeOut = pulseFadeOut;
        this.pulseMode = Mode.COLOR;
        this.pulseTargetType = pulseTargetType;
        this.mainOnly = mainOnly;
        this.detailOnly = !mainOnly && detailOnly;
        this.exclusive = exclusive;
        this.targetID = targetID;
    }

    public PulseTrigger(final boolean spawnTriggered, final boolean multiTriggered, final boolean blending, final float opacity,
                        @Nullable final ColorHSBModifier copiedColorHSBModifier, final int copiedColorChannelIndex, final boolean copyOpacity,
                        final float pulseFadeIn, final float pulseHold, final float pulseFadeOut, @NotNull final Target pulseTargetType,
                        final boolean mainOnly, final boolean detailOnly, final boolean exclusive, final int targetID) {
        super(spawnTriggered, multiTriggered);
        this.blending = blending;
        this.opacity = opacity;
        this.copiedColorHSBModifier = copiedColorHSBModifier;
        this.copiedColorChannelIndex = copiedColorChannelIndex;
        this.copyOpacity = copyOpacity;
        this.pulseFadeIn = pulseFadeIn;
        this.pulseHold = pulseHold;
        this.pulseFadeOut = pulseFadeOut;
        this.pulseMode = Mode.HSB;
        this.pulseTargetType = pulseTargetType;
        this.mainOnly = mainOnly;
        this.detailOnly = !mainOnly && detailOnly;
        this.exclusive = exclusive;
        this.targetID = targetID;
    }

    public PulseTrigger(final boolean touchTriggered, final boolean blending, final float opacity,
                        @Nullable final ColorHSBModifier copiedColorHSBModifier, final int copiedColorChannelIndex, final boolean copyOpacity,
                        final float pulseFadeIn, final float pulseHold, final float pulseFadeOut, @NotNull final Target pulseTargetType,
                        final boolean mainOnly, final boolean detailOnly, final boolean exclusive, final int targetID) {
        super(touchTriggered);
        this.blending = blending;
        this.opacity = opacity;
        this.copiedColorHSBModifier = copiedColorHSBModifier;
        this.copiedColorChannelIndex = copiedColorChannelIndex;
        this.copyOpacity = copyOpacity;
        this.pulseFadeIn = pulseFadeIn;
        this.pulseHold = pulseHold;
        this.pulseFadeOut = pulseFadeOut;
        this.pulseMode = Mode.HSB;
        this.pulseTargetType = pulseTargetType;
        this.mainOnly = mainOnly;
        this.detailOnly = !mainOnly && detailOnly;
        this.exclusive = exclusive;
        this.targetID = targetID;
    }

    public PulseTrigger(final boolean blending, final float opacity, @Nullable final ColorHSBModifier copiedColorHSBModifier,
                        final int copiedColorChannelIndex, final boolean copyOpacity, final float pulseFadeIn, final float pulseHold, final float pulseFadeOut,
                        @NotNull final Target pulseTargetType, final boolean mainOnly, final boolean detailOnly, final boolean exclusive, final int targetID) {
        this.blending = blending;
        this.opacity = opacity;
        this.copiedColorHSBModifier = copiedColorHSBModifier;
        this.copiedColorChannelIndex = copiedColorChannelIndex;
        this.copyOpacity = copyOpacity;
        this.pulseFadeIn = pulseFadeIn;
        this.pulseHold = pulseHold;
        this.pulseFadeOut = pulseFadeOut;
        this.pulseMode = Mode.HSB;
        this.pulseTargetType = pulseTargetType;
        this.mainOnly = mainOnly;
        this.detailOnly = !mainOnly && detailOnly;
        this.exclusive = exclusive;
        this.targetID = targetID;
    }

    public int redComponent() {
        return redComponent;
    }

    public void redComponent(final int redComponent) {
        this.redComponent = redComponent;
    }

    public int greenComponent() {
        return greenComponent;
    }

    public void greenComponent(final int greenComponent) {
        this.greenComponent = greenComponent;
    }

    public int blueComponent() {
        return blueComponent;
    }

    public void blueComponent(final int blueComponent) {
        this.blueComponent = blueComponent;
    }

    public boolean blending() {
        return blending;
    }

    public void blending(final boolean blending) {
        this.blending = blending;
    }

    public float opacity() {
        return opacity;
    }

    public void opacity(final float opacity) {
        this.opacity = opacity;
    }

    @NotNull
    public Optional<ColorHSBModifier> copiedColorHSBModifier() {
        return Optional.ofNullable(copiedColorHSBModifier);
    }

    public void copiedColorHSBModifier(@Nullable final ColorHSBModifier copiedColorHSBModifier) {
        this.copiedColorHSBModifier = copiedColorHSBModifier;
    }

    public int copiedColorChannelIndex() {
        return copiedColorChannelIndex;
    }

    public void copiedColorChannelIndex(final int copiedColorChannelIndex) {
        this.copiedColorChannelIndex = copiedColorChannelIndex;
    }

    public boolean copyOpacity() {
        return copyOpacity;
    }

    public void copyOpacity(final boolean copyOpacity) {
        this.copyOpacity = copyOpacity;
    }

    public float pulseFadeIn() {
        return pulseFadeIn;
    }

    public void pulseFadeIn(final float pulseFadeIn) {
        this.pulseFadeIn = pulseFadeIn;
    }

    public float pulseHold() {
        return pulseHold;
    }

    public void pulseHold(final float pulseHold) {
        this.pulseHold = pulseHold;
    }

    public float pulseFadeOut() {
        return pulseFadeOut;
    }

    public void pulseFadeOut(final float pulseFadeOut) {
        this.pulseFadeOut = pulseFadeOut;
    }

    @NotNull
    public Mode pulseMode() {
        return pulseMode;
    }

    public void pulseMode(@NotNull final Mode pulseMode) {
        this.pulseMode = pulseMode;
    }

    @NotNull
    public Target pulseTargetType() {
        return pulseTargetType;
    }

    public void pulseTargetType(@NotNull final Target pulseTargetType) {
        this.pulseTargetType = pulseTargetType;
    }

    public boolean mainOnly() {
        return mainOnly;
    }

    public void mainOnly(final boolean mainOnly) {
        this.mainOnly = mainOnly;
    }

    public boolean detailOnly() {
        return detailOnly;
    }

    public void detailOnly(final boolean detailOnly) {
        this.detailOnly = detailOnly;
    }

    public boolean exclusive() {
        return exclusive;
    }

    public void exclusive(final boolean exclusive) {
        this.exclusive = exclusive;
    }

    public int targetID() {
        return targetID;
    }

    public void targetID(final int targetGroupID) {
        this.targetID = targetGroupID;
    }

}
