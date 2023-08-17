package org.crayne.gdboard.savefile.property.data;

import org.crayne.gdboard.savefile.property.PropertyDataType;
import org.jetbrains.annotations.NotNull;

public interface PropertyData {

    @NotNull
    PropertyDataType datatype();

    @NotNull
    String idString();

    @NotNull
    Object defaultValue();

}
