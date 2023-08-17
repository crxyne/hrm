package org.crayne.gdboard.level.data.object.type;

import org.crayne.gdboard.level.data.object.ObjectID;
import org.crayne.gdboard.level.data.object.ZLayer;
import org.crayne.gdboard.level.data.object.type.decoration.PulsatingObject;
import org.crayne.gdboard.level.data.object.type.decoration.RotatingObject;
import org.crayne.gdboard.level.data.object.type.decoration.TextObject;
import org.crayne.gdboard.level.data.object.type.general.OrbObject;
import org.crayne.gdboard.level.data.object.type.general.ToggleOrbObject;
import org.crayne.gdboard.level.data.object.type.portal.SpecialPortalObject;
import org.crayne.gdboard.level.data.object.type.portal.TeleportPortalObject;
import org.crayne.gdboard.level.data.object.type.trigger.collision.CollisionBlockObject;
import org.crayne.gdboard.level.data.object.type.trigger.collision.CollisionTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.SpawnTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.StartPositionTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.StopTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.general.TouchTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.count.CountTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.count.InstantCountTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.item.count.ItemCounter;
import org.crayne.gdboard.level.data.object.type.trigger.item.pickup.PickupItemObject;
import org.crayne.gdboard.level.data.object.type.trigger.item.pickup.PickupTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.FollowPlayerYTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.FollowTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.MoveTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.movement.RotateTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.AlphaTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.AnimateTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.ShakeTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.color.ColorTrigger;
import org.crayne.gdboard.level.data.object.type.trigger.visual.color.PulseTrigger;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class LevelObject {

    @Nullable
    private final String sprite;

    private int objectID;
    private float positionX, positionY;
    private boolean flippedHorizontally, flippedVertically;
    private float rotation;
    private int editorLayer1, editorLayer2;

    @NotNull
    private ZLayer zLayer;
    private int zOrder;
    private float scaling;

    @NotNull
    private List<Integer> groupIDs;
    private boolean groupParent;
    private boolean dontFade, dontEnter;
    private boolean disableGlow;
    private boolean highDetailObject;
    private int linkedGroupID;

    public LevelObject(@NotNull final Properties objectProperties) {
        this.objectID            = objectProperties.integerProperty(LevelObjectData.OBJECT_ID);
        this.positionX           = objectProperties.floatProperty(LevelObjectData.X_POSITION);
        this.positionY           = objectProperties.floatProperty(LevelObjectData.Y_POSITION);
        this.flippedHorizontally = objectProperties.booleanProperty(LevelObjectData.FLIPPED_HORIZ);
        this.flippedVertically   = objectProperties.booleanProperty(LevelObjectData.FLIPPED_VERT);
        this.rotation            = objectProperties.floatProperty(LevelObjectData.ROTATION);
        this.editorLayer1        = objectProperties.integerProperty(LevelObjectData.EDITOR_L1);
        this.editorLayer2        = objectProperties.integerProperty(LevelObjectData.EDITOR_L2);
        this.zLayer              = objectProperties.zLayerProperty(objectID);
        this.zOrder              = objectProperties.zOrderProperty(objectID);
        this.scaling             = objectProperties.floatProperty(LevelObjectData.SCALING);
        this.groupIDs            = objectProperties.integerArrayProperty(LevelObjectData.GROUP_IDS);
        this.groupParent         = objectProperties.booleanProperty(LevelObjectData.GROUP_PARENT);
        this.dontFade            = objectProperties.booleanProperty(LevelObjectData.DONT_FADE);
        this.dontEnter           = objectProperties.booleanProperty(LevelObjectData.DONT_ENTER);
        this.disableGlow         = objectProperties.booleanProperty(LevelObjectData.DISABLE_GLOW);
        this.highDetailObject    = objectProperties.booleanProperty(LevelObjectData.HIGH_DETAIL);
        this.linkedGroupID       = objectProperties.integerProperty(LevelObjectData.LINKED_GROUP_ID);

        this.sprite = ObjectID.spriteForObjectID(objectID).orElse(null);
    }

    public LevelObject(final int objectID, final float positionX, final float positionY) {
        this.objectID = objectID;
        this.zLayer = ObjectID.defaultZLayerOfObjectID(objectID);
        this.positionX = positionX;
        this.positionY = positionY;
        this.groupIDs = new ArrayList<>();

        this.sprite = ObjectID.spriteForObjectID(objectID).orElse(null);
    }

    public LevelObject(@NotNull final LevelObject levelObject) {
        this.objectID = levelObject.objectID;
        this.positionX = levelObject.positionX;
        this.positionY = levelObject.positionY;
        this.flippedHorizontally = levelObject.flippedHorizontally;
        this.flippedVertically = levelObject.flippedVertically;
        this.rotation = levelObject.rotation;
        this.editorLayer1 = levelObject.editorLayer1;
        this.editorLayer2 = levelObject.editorLayer2;
        this.zLayer = levelObject.zLayer;
        this.zOrder = levelObject.zOrder;
        this.scaling = levelObject.scaling;
        this.groupIDs = levelObject.groupIDs;
        this.groupParent = levelObject.groupParent;
        this.dontFade = levelObject.dontFade;
        this.dontEnter = levelObject.dontEnter;
        this.disableGlow = levelObject.disableGlow;
        this.highDetailObject = levelObject.highDetailObject;
        this.linkedGroupID = levelObject.linkedGroupID;

        this.sprite = ObjectID.spriteForObjectID(objectID).orElse(null);
    }

    @NotNull
    public static LevelObject parse(@NotNull final Properties properties) {
        final int objectID = properties.integerProperty(LevelObjectData.OBJECT_ID);

        if (ObjectID.isPulsatingObject(objectID)) return new PulsatingObject(properties);
        if (ObjectID.isRotatingObject(objectID)) return new RotatingObject(properties);
        if (ObjectID.isTextObject(objectID)) return new TextObject(properties);
        if (ObjectID.isItemCounter(objectID)) return new ItemCounter(properties);
        if (ObjectID.isToggleOrbObject(objectID)) return new ToggleOrbObject(properties);
        if (ObjectID.isOrbObject(objectID)) return new OrbObject(properties);
        if (ObjectID.isSpecialPropertyPortal(objectID)) return new SpecialPortalObject(properties);
        if (ObjectID.isTeleportPortal(objectID)) return new TeleportPortalObject(properties);
        if (ObjectID.isCollisionBlockObject(objectID)) return new CollisionBlockObject(properties);
        if (ObjectID.isPickupItemObject(objectID)) return new PickupItemObject(properties);

        return switch (ObjectID.of(objectID)) {
            case START_POSITION_TRIGGER  -> new StartPositionTrigger(properties);
            case COLOR_TRIGGER           -> new ColorTrigger(properties);
            case MOVE_TRIGGER            -> new MoveTrigger(properties);
            case PULSE_TRIGGER           -> new PulseTrigger(properties);
            case ALPHA_TRIGGER           -> new AlphaTrigger(properties);
            case TOGGLE_TRIGGER,
                    ON_DEATH_TRIGGER     -> new ToggleTrigger(properties);
            case SPAWN_TRIGGER           -> new SpawnTrigger(properties);
            case ROTATE_TRIGGER          -> new RotateTrigger(properties);
            case FOLLOW_TRIGGER          -> new FollowTrigger(properties);
            case SHAKE_TRIGGER           -> new ShakeTrigger(properties);
            case ANIMATE_TRIGGER         -> new AnimateTrigger(properties);
            case TOUCH_TRIGGER           -> new TouchTrigger(properties);
            case COUNT_TRIGGER           -> new CountTrigger(properties);
            case STOP_TRIGGER            -> new StopTrigger(properties);
            case INSTANT_COUNT_TRIGGER   -> new InstantCountTrigger(properties);
            case FOLLOW_PLAYER_Y_TRIGGER -> new FollowPlayerYTrigger(properties);
            case COLLISION_TRIGGER       -> new CollisionTrigger(properties);
            case PICKUP_TRIGGER          -> new PickupTrigger(properties);
            case UNKNOWN                 -> new LevelObject(properties);
        };
    }

    @NotNull
    public Optional<String> sprite() {
        return Optional.ofNullable(sprite);
    }

    public int objectID() {
        return objectID;
    }

    public void objectID(final int objectID) {
        this.objectID = objectID;
    }

    public float positionX() {
        return positionX;
    }

    public void positionX(final float positionX) {
        this.positionX = positionX;
    }

    public float positionY() {
        return positionY;
    }

    public void positionY(final float positionY) {
        this.positionY = positionY;
    }

    public boolean flippedHorizontally() {
        return flippedHorizontally;
    }

    public void flippedHorizontally(final boolean flippedHorizontally) {
        this.flippedHorizontally = flippedHorizontally;
    }

    public boolean flippedVertically() {
        return flippedVertically;
    }

    public void flippedVertically(final boolean flippedVertically) {
        this.flippedVertically = flippedVertically;
    }

    public float rotation() {
        return rotation;
    }

    public void rotation(final float rotation) {
        this.rotation = rotation;
    }

    public int editorLayer1() {
        return editorLayer1;
    }

    public void editorLayer1(final int editorLayer1) {
        this.editorLayer1 = editorLayer1;
    }

    public int editorLayer2() {
        return editorLayer2;
    }

    public void editorLayer2(final int editorLayer2) {
        this.editorLayer2 = editorLayer2;
    }

    @NotNull
    public ZLayer zLayer() {
        return zLayer;
    }

    public void zLayer(@NotNull final ZLayer zLayer) {
        this.zLayer = zLayer;
    }

    public int zOrder() {
        return zOrder;
    }

    public void zOrder(final int zOrder) {
        this.zOrder = zOrder;
    }

    public float scaling() {
        return scaling;
    }

    public void scaling(final float scaling) {
        this.scaling = scaling;
    }

    @NotNull
    public List<Integer> groupIDs() {
        return groupIDs;
    }

    public void groupIDs(@NotNull final Collection<Integer> groupIDs) {
        this.groupIDs = new ArrayList<>(groupIDs);
    }

    public boolean groupParent() {
        return groupParent;
    }

    public void groupParent(final boolean groupParent) {
        this.groupParent = groupParent;
    }

    public boolean dontFade() {
        return dontFade;
    }

    public void dontFade(final boolean dontFade) {
        this.dontFade = dontFade;
    }

    public boolean dontEnter() {
        return dontEnter;
    }

    public void dontEnter(final boolean dontEnter) {
        this.dontEnter = dontEnter;
    }

    public boolean disableGlow() {
        return disableGlow;
    }

    public void disableGlow(final boolean disableGlow) {
        this.disableGlow = disableGlow;
    }

    public boolean highDetailObject() {
        return highDetailObject;
    }

    public void highDetailObject(final boolean highDetailObject) {
        this.highDetailObject = highDetailObject;
    }

    public int linkedGroupID() {
        return linkedGroupID;
    }

    public void linkedGroupID(final int linkedGroupID) {
        this.linkedGroupID = linkedGroupID;
    }

    @NotNull
    public String toString() {
        return "LevelObject{" +
                "sprite='" + sprite + '\'' +
                ", objectID=" + objectID +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", flippedHorizontally=" + flippedHorizontally +
                ", flippedVertically=" + flippedVertically +
                ", rotation=" + rotation +
                ", editorLayer1=" + editorLayer1 +
                ", editorLayer2=" + editorLayer2 +
                ", zLayer=" + zLayer +
                ", zOrder=" + zOrder +
                ", scaling=" + scaling +
                ", groupIDs=" + groupIDs +
                ", groupParent=" + groupParent +
                ", dontFade=" + dontFade +
                ", dontEnter=" + dontEnter +
                ", disableGlow=" + disableGlow +
                ", highDetailObject=" + highDetailObject +
                ", linkedGroupID=" + linkedGroupID +
                '}';
    }

}
