package org.crayne.gdboard.level.data;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LevelEditorCamera {

    private float positionX, positionY, zoom;

    public LevelEditorCamera(final float positionX, final float positionY, final float zoom) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.zoom = zoom;
    }

    @NotNull
    public static LevelEditorCamera defaultCamera() {
        return new LevelEditorCamera(0, 0, 1);
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

    public float zoom() {
        return zoom;
    }

    public void zoom(final float zoom) {
        this.zoom = zoom;
    }

    @NotNull
    public String toString() {
        return "LevelEditorCamera{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                ", zoom=" + zoom +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LevelEditorCamera that = (LevelEditorCamera) o;

        if (Float.compare(that.positionX, positionX) != 0) return false;
        if (Float.compare(that.positionY, positionY) != 0) return false;
        return Float.compare(that.zoom, zoom) == 0;
    }

    public int hashCode() {
        int result = (positionX != +0.0f ? Float.floatToIntBits(positionX) : 0);
        result = 31 * result + (positionY != +0.0f ? Float.floatToIntBits(positionY) : 0);
        result = 31 * result + (zoom != +0.0f ? Float.floatToIntBits(zoom) : 0);
        return result;
    }
}
