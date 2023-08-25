package org.crayne.hrm.api.level.data.object;

import org.crayne.hrm.api.savefile.property.PropertyDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public enum ZLayer {

    B4(-3),
    B3(-1),
    B2(1),
    B1(3),
    T1(5),
    T2(7),
    T3(9),
    UNKNOWN(0);

    private final int id;

    ZLayer(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    @NotNull
    public static PropertyDataType datatype() {
        return PropertyDataType.Z_LAYER;
    }

    @NotNull
    public static ZLayer of(final int id) {
        return Arrays.stream(values()).filter(zLayer -> zLayer.id == id).findAny().orElse(T1);
    }

}
