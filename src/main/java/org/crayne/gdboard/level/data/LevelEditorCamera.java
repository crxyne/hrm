package org.crayne.gdboard.level;

import org.jetbrains.annotations.NotNull;

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

}
