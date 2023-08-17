package org.crayne.gdboard.level.data.object.type;

import org.crayne.gdboard.level.data.color.ColorHSBModifier;
import org.jetbrains.annotations.Nullable;

public class AbstractObject {

    // TODO fix this shitty code and only save the values required (make a class for every object that has any extra properties)

    // all objects
    private int objectID; // 1
    private float positionX, positionY; // 2, 3
    private boolean flippedHorizontally, flippedVertically; // 4, 5
    private float rotation; // 6
    private int editorLayer1, editorLayer2; // 20, 61
    private int zLayer, zOrder; // 24, 25
    private float scaling; // 32
    private int singleGroupID; // 33
    private int[] groupIDs; // 57
    private boolean groupParent; // 34
    private boolean dontFade, dontEnter; // 64, 67
    private boolean disableGlow; // 96
    private boolean highDetailObject; // 103
    private int linkedGroupID; // 108

    // orbs
    protected boolean multiActiveOrb; // 99

    // portals
    protected boolean specialPropertyChecked; // 13

    // teleportal
    protected float teleportYOffset; // 54
    protected boolean teleportEase; // 55

    // color trigger
    protected int redComponent, greenComponent, blueComponent; // 7, 8, 9
    protected boolean playerColor1, playerColor2; // 15, 16
    protected boolean blending; // 17
    protected int targetColorChannelIndex; // 23
    protected float opacity; // 35

    // pulse trigger
    protected float pulseFadeIn, pulseHold, pulseFadeOut; // 45, 46, 47
    protected int pulseMode; // 48

    @Nullable
    protected ColorHSBModifier copiedColorHSBModifier; // 49

    protected int copiedColorChannelIndex; // 50
    protected int pulseTargetType; // 52
    protected boolean copyOpacity; // 60
    protected boolean mainOnly, detailOnly; // 65, 66
    protected boolean exclusive; // 86

    // move trigger
    protected int moveOffsetX, moveOffsetY; // 28, 29
    protected int easingType; // 30
    protected float easingRate; // 85
    protected boolean lockToPlayerX, lockToPlayerY; // 58, 59
    protected boolean useMoveTarget; // 100
    protected int targetPosCoordinates; // 101

    // follow trigger
    protected float modifierX, modifierY; // 72, 73

    // follow player y trigger
    protected float followDelay; // 91
    protected float followYOffset; // 92
    protected float maxFollowSpeed; // 105

    // rotate trigger
    protected int degrees; // 68
    protected int times360; // 69
    protected boolean lockObjectRotation; // 70

    // any form of toggle trigger
    protected boolean activateGroup; // 56

    // spawn trigger
    protected float spawnDelay; // 63
    protected boolean editorDisable; // 102

    // shake trigger
    protected float shakeStrength; // 75
    protected float interval; // 84

    // animate trigger / object
    protected int animationID; // 76
    protected boolean randomizeStart; // 106
    protected float animationSpeed; // 107

    // pickup trigger / item
    protected int count; // 77
    protected boolean subtractCount; // 78
    protected int pickupMode; // 79

    // instant count
    protected int instantCountComparison; // 88

    // touch trigger
    protected boolean touchHoldMode; // 81
    protected int touchToggleMode; // 82
    protected boolean touchDualMode; // 89

    // collision trigger
    protected boolean triggerOnExit, dynamicBlock;  // 93, 94

    // general triggers
    protected float triggerDuration; // 10
    protected int targetGroupID; // 51
    protected int secondaryGroupID; // 71
    protected int itemBlockID; // 80
    protected int secondBlockID; // 95

    // all triggers
    protected boolean touchTriggered; // 11
    protected boolean spawnTriggered; // 62
    protected boolean multiTriggered; // 104



}
