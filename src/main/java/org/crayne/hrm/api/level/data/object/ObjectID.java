package org.crayne.hrm.api.level.data.object;

import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.object.type.decoration.ColorableObject;
import org.crayne.hrm.api.level.data.object.type.decoration.PulsatingObject;
import org.crayne.hrm.api.level.data.object.type.decoration.RotatingObject;
import org.crayne.hrm.api.level.data.object.type.decoration.TextObject;
import org.crayne.hrm.api.level.data.object.type.general.OrbObject;
import org.crayne.hrm.api.level.data.object.type.general.ToggleOrbObject;
import org.crayne.hrm.api.level.data.object.type.portal.SpecialPortalObject;
import org.crayne.hrm.api.level.data.object.type.portal.TeleportPortalObject;
import org.crayne.hrm.api.level.data.object.type.trigger.collision.CollisionBlockObject;
import org.crayne.hrm.api.level.data.object.type.trigger.collision.CollisionTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.general.SpawnTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.general.StartPositionTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.general.StopTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.general.TouchTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.count.CountTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.count.InstantCountTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.item.count.ItemCounter;
import org.crayne.hrm.api.level.data.object.type.trigger.item.pickup.PickupItemObject;
import org.crayne.hrm.api.level.data.object.type.trigger.item.pickup.PickupTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.FollowPlayerYTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.FollowTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.MoveTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.movement.RotateTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.toggle.ToggleTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.AlphaTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.AnimateTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.ShakeTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.color.ColorTrigger;
import org.crayne.hrm.api.level.data.object.type.trigger.visual.color.PulseTrigger;
import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ObjectID {

    private ObjectID() {}

    @NotNull
    private static final Map<Integer, Function<Properties, LevelObject>> PROPERTIES_PARSERS = new HashMap<>();

    private static void putAllObjectIDs(@NotNull final Set<Integer> objectIDs, @NotNull final Function<Properties, LevelObject> function) {
        objectIDs.forEach(id -> PROPERTIES_PARSERS.put(id, function));
    }

    static {
        putAllObjectIDs(PulsatingObject.objectIDs(), PulsatingObject::new);
        putAllObjectIDs(RotatingObject.objectIDs(), RotatingObject::new);
        putAllObjectIDs(TextObject.objectIDs(), TextObject::new);
        putAllObjectIDs(ItemCounter.objectIDs(), ItemCounter::new);
        putAllObjectIDs(StartPositionTrigger.objectIDs(), StartPositionTrigger::new);
        putAllObjectIDs(ColorTrigger.objectIDs(), ColorTrigger::new);
        putAllObjectIDs(MoveTrigger.objectIDs(), MoveTrigger::new);
        putAllObjectIDs(PulseTrigger.objectIDs(), PulseTrigger::new);
        putAllObjectIDs(ToggleOrbObject.objectIDs(), ToggleOrbObject::new);
        putAllObjectIDs(OrbObject.objectIDs(), OrbObject::new);
        putAllObjectIDs(SpecialPortalObject.objectIDs(), SpecialPortalObject::new);
        putAllObjectIDs(TeleportPortalObject.objectIDs(), TeleportPortalObject::new);
        putAllObjectIDs(CollisionBlockObject.objectIDs(), CollisionBlockObject::new);
        putAllObjectIDs(PickupItemObject.objectIDs(), PickupItemObject::new);
        putAllObjectIDs(AlphaTrigger.objectIDs(), AlphaTrigger::new);
        putAllObjectIDs(ToggleTrigger.objectIDs(), ToggleTrigger::new);
        putAllObjectIDs(SpawnTrigger.objectIDs(), SpawnTrigger::new);
        putAllObjectIDs(RotateTrigger.objectIDs(), RotateTrigger::new);
        putAllObjectIDs(FollowTrigger.objectIDs(), FollowTrigger::new);
        putAllObjectIDs(ShakeTrigger.objectIDs(), ShakeTrigger::new);
        putAllObjectIDs(AnimateTrigger.objectIDs(), AnimateTrigger::new);
        putAllObjectIDs(TouchTrigger.objectIDs(), TouchTrigger::new);
        putAllObjectIDs(CountTrigger.objectIDs(), CountTrigger::new);
        putAllObjectIDs(StopTrigger.objectIDs(), StopTrigger::new);
        putAllObjectIDs(InstantCountTrigger.objectIDs(), InstantCountTrigger::new);
        putAllObjectIDs(FollowPlayerYTrigger.objectIDs(), FollowPlayerYTrigger::new);
        putAllObjectIDs(CollisionTrigger.objectIDs(), CollisionTrigger::new);
        putAllObjectIDs(PickupTrigger.objectIDs(), PickupTrigger::new);
        putAllObjectIDs(ColorableObject.objectIDs(), ColorableObject::new);
    }

    @NotNull
    public static LevelObject parse(@NotNull final Properties properties) {
        final int objectID = properties.integerProperty(LevelObjectProperty.OBJECT_ID);
        final Function<Properties, LevelObject> levelObjectParser = PROPERTIES_PARSERS.get(objectID);
        if (levelObjectParser == null) return new LevelObject(properties);

        return levelObjectParser.apply(properties);
    }

}
