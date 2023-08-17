package org.crayne.gdboard.level.data.object.type.trigger.color;

import org.crayne.gdboard.level.data.color.ColorHSBModifier;
import org.crayne.gdboard.level.data.object.type.trigger.Trigger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("unused")
public class ColorTrigger extends Trigger {

    private int redComponent, greenComponent, blueComponent; // 7, 8, 9
    private boolean playerColor1, playerColor2; // 15, 16
    private boolean blending; // 17
    private int targetColorChannelIndex; // 23
    private float opacity; // 35

    @Nullable
    private ColorHSBModifier copiedColorHSBModifier; // 49
    private int copiedColorChannelIndex; // 50
    private boolean copyOpacity; // 60

    private float triggerDuration; // 10

    public ColorTrigger(final boolean spawnTriggered, final boolean multiTriggered, final int redComponent, final int greenComponent, final int blueComponent,
                        final boolean blending, final int targetColorChannelIndex, final float opacity, final float triggerDuration) {
        super(spawnTriggered, multiTriggered);
        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final boolean touchTriggered, final int redComponent, final int greenComponent, final int blueComponent, final boolean blending,
                        final int targetColorChannelIndex, final float opacity, final float triggerDuration) {
        super(touchTriggered);
        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final int redComponent, final int greenComponent, final int blueComponent, final boolean blending, final int targetColorChannelIndex,
                        final float opacity, final float triggerDuration) {
        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final boolean spawnTriggered, final boolean multiTriggered, final boolean playerColor1, final boolean playerColor2,
                        final boolean blending, final int targetColorChannelIndex, final float opacity, final float triggerDuration) {
        super(spawnTriggered, multiTriggered);
        this.playerColor1 = playerColor1;
        this.playerColor2 = !playerColor1 && playerColor2;
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final boolean touchTriggered, final boolean playerColor1, final boolean playerColor2, final boolean blending,
                        final int targetColorChannelIndex, final float opacity, final float triggerDuration) {
        super(touchTriggered);
        this.playerColor1 = playerColor1;
        this.playerColor2 = !playerColor1 && playerColor2;
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final boolean playerColor1, final boolean playerColor2, final boolean blending, final int targetColorChannelIndex,
                        final float opacity, final float triggerDuration) {
        this.playerColor1 = playerColor1;
        this.playerColor2 = !playerColor1 && playerColor2;
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final boolean spawnTriggered, final boolean multiTriggered, final boolean blending, final int targetColorChannelIndex,
                        final float opacity, @Nullable final ColorHSBModifier copiedColorHSBModifier, final int copiedColorChannelIndex,
                        final boolean copyOpacity, final float triggerDuration) {
        super(spawnTriggered, multiTriggered);
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.copiedColorHSBModifier = copiedColorHSBModifier;
        this.copiedColorChannelIndex = copiedColorChannelIndex;
        this.copyOpacity = copyOpacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final boolean touchTriggered, final boolean blending, final int targetColorChannelIndex, final float opacity,
                        @Nullable final ColorHSBModifier copiedColorHSBModifier, final int copiedColorChannelIndex, final boolean copyOpacity,
                        final float triggerDuration) {
        super(touchTriggered);
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.copiedColorHSBModifier = copiedColorHSBModifier;
        this.copiedColorChannelIndex = copiedColorChannelIndex;
        this.copyOpacity = copyOpacity;
        this.triggerDuration = triggerDuration;
    }

    public ColorTrigger(final boolean blending, final int targetColorChannelIndex, final float opacity, @Nullable final ColorHSBModifier copiedColorHSBModifier,
                        final int copiedColorChannelIndex, final boolean copyOpacity, final float triggerDuration) {
        this.blending = blending;
        this.targetColorChannelIndex = targetColorChannelIndex;
        this.opacity = opacity;
        this.copiedColorHSBModifier = copiedColorHSBModifier;
        this.copiedColorChannelIndex = copiedColorChannelIndex;
        this.copyOpacity = copyOpacity;
        this.triggerDuration = triggerDuration;
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

    public boolean playerColor1() {
        return playerColor1;
    }

    public void playerColor1(final boolean playerColor1) {
        this.playerColor1 = playerColor1;
        this.playerColor2 = !playerColor1;
    }

    public boolean playerColor2() {
        return playerColor2;
    }

    public void playerColor2(final boolean playerColor2) {
        this.playerColor2 = playerColor2;
        this.playerColor1 = !playerColor2;
    }

    public boolean blending() {
        return blending;
    }

    public void blending(final boolean blending) {
        this.blending = blending;
    }

    public int targetColorChannelIndex() {
        return targetColorChannelIndex;
    }

    public void targetColorChannelIndex(final int targetColorChannelIndex) {
        this.targetColorChannelIndex = targetColorChannelIndex;
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

    public float triggerDuration() {
        return triggerDuration;
    }

    public void triggerDuration(final float triggerDuration) {
        this.triggerDuration = triggerDuration;
    }

}
