package org.crayne.gdboard.level.data.object.type;

import org.crayne.gdboard.level.data.object.ObjectID;
import org.crayne.gdboard.level.data.object.ZLayer;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class LevelObject {

    @Nullable
    private final transient String sprite;

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
        this.objectID            = objectProperties.integerProperty(LevelObjectProperty.OBJECT_ID);
        this.positionX           = objectProperties.floatProperty(LevelObjectProperty.X_POSITION);
        this.positionY           = objectProperties.floatProperty(LevelObjectProperty.Y_POSITION);
        this.flippedHorizontally = objectProperties.booleanProperty(LevelObjectProperty.FLIPPED_HORIZ);
        this.flippedVertically   = objectProperties.booleanProperty(LevelObjectProperty.FLIPPED_VERT);
        this.rotation            = objectProperties.floatProperty(LevelObjectProperty.ROTATION);
        this.editorLayer1        = objectProperties.integerProperty(LevelObjectProperty.EDITOR_L1);
        this.editorLayer2        = objectProperties.integerProperty(LevelObjectProperty.EDITOR_L2);
        this.zLayer              = objectProperties.zLayerProperty(objectID);
        this.zOrder              = objectProperties.zOrderProperty(objectID);
        this.scaling             = objectProperties.floatProperty(LevelObjectProperty.SCALING);
        this.groupIDs            = objectProperties.integerArrayProperty(LevelObjectProperty.GROUP_IDS);
        this.groupParent         = objectProperties.booleanProperty(LevelObjectProperty.GROUP_PARENT);
        this.dontFade            = objectProperties.booleanProperty(LevelObjectProperty.DONT_FADE);
        this.dontEnter           = objectProperties.booleanProperty(LevelObjectProperty.DONT_ENTER);
        this.disableGlow         = objectProperties.booleanProperty(LevelObjectProperty.DISABLE_GLOW);
        this.highDetailObject    = objectProperties.booleanProperty(LevelObjectProperty.HIGH_DETAIL);
        this.linkedGroupID       = objectProperties.integerProperty(LevelObjectProperty.LINKED_GROUP_ID);
        this.sprite = ObjectID.spriteForObjectID(objectID);
    }

    public LevelObject(final int objectID, final float positionX, final float positionY) {
        this.objectID = objectID;
        this.zLayer = ObjectID.defaultZLayerOfObjectID(objectID);
        this.positionX = positionX;
        this.positionY = positionY;
        this.groupIDs = new ArrayList<>();

        this.sprite = ObjectID.spriteForObjectID(objectID);
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

        this.sprite = ObjectID.spriteForObjectID(objectID);
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

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LevelObject that = (LevelObject) o;

        if (objectID != that.objectID) return false;
        if (Float.compare(that.positionX, positionX) != 0) return false;
        if (Float.compare(that.positionY, positionY) != 0) return false;
        if (flippedHorizontally != that.flippedHorizontally) return false;
        if (flippedVertically != that.flippedVertically) return false;
        if (Float.compare(that.rotation, rotation) != 0) return false;
        if (editorLayer1 != that.editorLayer1) return false;
        if (editorLayer2 != that.editorLayer2) return false;
        if (zOrder != that.zOrder) return false;
        if (Float.compare(that.scaling, scaling) != 0) return false;
        if (groupParent != that.groupParent) return false;
        if (dontFade != that.dontFade) return false;
        if (dontEnter != that.dontEnter) return false;
        if (disableGlow != that.disableGlow) return false;
        if (highDetailObject != that.highDetailObject) return false;
        if (linkedGroupID != that.linkedGroupID) return false;
        if (zLayer != that.zLayer) return false;
        return groupIDs.equals(that.groupIDs);
    }

    public int hashCode() {
        int result = objectID;
        result = 31 * result + (positionX != 0.0f ? Float.floatToIntBits(positionX) : 0);
        result = 31 * result + (positionY != 0.0f ? Float.floatToIntBits(positionY) : 0);
        result = 31 * result + (flippedHorizontally ? 1 : 0);
        result = 31 * result + (flippedVertically ? 1 : 0);
        result = 31 * result + (rotation != 0.0f ? Float.floatToIntBits(rotation) : 0);
        result = 31 * result + editorLayer1;
        result = 31 * result + editorLayer2;
        result = 31 * result + zLayer.hashCode();
        result = 31 * result + zOrder;
        result = 31 * result + (scaling != 0.0f ? Float.floatToIntBits(scaling) : 0);
        result = 31 * result + groupIDs.hashCode();
        result = 31 * result + (groupParent ? 1 : 0);
        result = 31 * result + (dontFade ? 1 : 0);
        result = 31 * result + (dontEnter ? 1 : 0);
        result = 31 * result + (disableGlow ? 1 : 0);
        result = 31 * result + (highDetailObject ? 1 : 0);
        result = 31 * result + linkedGroupID;
        return result;
    }
}
