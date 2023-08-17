package org.crayne.gdboard.level.data.object.type.portal;

import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.data.LevelObjectData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class TeleportPortalObject extends LevelObject {

    private float teleportYOffset;
    private boolean teleportEase;

    public TeleportPortalObject(final int objectID, final float positionX, final float positionY,
                                final float teleportYOffset, final boolean teleportEase) {
        super(objectID, positionX, positionY);
        this.teleportYOffset = teleportYOffset;
        this.teleportEase = teleportEase;
    }

    public TeleportPortalObject(@NotNull final LevelObject levelObject, final float teleportYOffset,
                                final boolean teleportEase) {
        super(levelObject);
        this.teleportYOffset = teleportYOffset;
        this.teleportEase = teleportEase;
    }

    public TeleportPortalObject(@NotNull final LevelObject levelObject) {
        super(levelObject);
    }

    public TeleportPortalObject(@NotNull final Properties objectProperties) {
        super(objectProperties);
        this.teleportYOffset = objectProperties.floatProperty(LevelObjectData.TELEPORT_Y_OFFSET);
        this.teleportEase = objectProperties.booleanProperty(LevelObjectData.TELEPORT_EASE);
    }

    public float teleportYOffset() {
        return teleportYOffset;
    }

    public void teleportYOffset(final float teleportYOffset) {
        this.teleportYOffset = teleportYOffset;
    }

    public boolean teleportEase() {
        return teleportEase;
    }

    public void teleportEase(final boolean teleportEase) {
        this.teleportEase = teleportEase;
    }
}
