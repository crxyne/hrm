package org.crayne.gdboard;

import org.crayne.gdboard.level.LocalLevel;
import org.crayne.gdboard.level.data.object.type.LevelObject;
import org.crayne.gdboard.savefile.decrypt.LevelDataDecryption;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(@NotNull final String... args) {
        final List<LocalLevel> levelList = LevelDataDecryption.decryptAllLevelData(new File("TestAdditionsDeletions.dat"));
        final LocalLevel level2 = levelList.get(0);
        final LocalLevel level1 = levelList.get(1);

        System.out.println("level 1 objs [" + level1.data().levelObjects().size() + "]:");
        level1.data().levelObjects().forEach(System.out::println);

        System.out.println();
        System.out.println("level 2 objs [" + level2.data().levelObjects().size() + "]:");
        level2.data().levelObjects().forEach(System.out::println);

        final List<LevelObject> removedObjects = new ArrayList<>(level1.data().levelObjects());
        removedObjects.removeAll(level2.data().levelObjects());

        final List<LevelObject> addedObjects = new ArrayList<>(level2.data().levelObjects());
        addedObjects.removeAll(level1.data().levelObjects());

        System.out.println();

        removedObjects.forEach(obj -> System.out.println(" - " + obj));
        addedObjects.forEach(obj -> System.out.println(" + " + obj));
    }


}