package org.crayne.hrm;

import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
import org.crayne.hrm.repository.HRMRepository;
import org.crayne.hrm.repository.commit.HRMCommit;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Main {

    @NotNull
    private static final String MY_OWN_GD_APPDATA_FOLDER_CONFIG_LATER = "/home/crayne/gd/Geometry Dash Board/GD-Savefile-Test/drive_c/users/crayne/AppData/Local/GeometryDash";

    public static void main2(@NotNull final String... args) {
        final HRMRepository repository = new HRMRepository("hrm test i guess", "HRMCollab", new File(MY_OWN_GD_APPDATA_FOLDER_CONFIG_LATER), new File("HRMCollab"));
        final boolean alreadyInitialized = !repository.init();
        if (alreadyInitialized) {
            System.out.println("Err: HRM repository was already initialized, nothing was changed.");
            System.exit(1);
            return;
        }
        System.out.println("Successfully created HRM repository in " + repository.directory().getAbsolutePath());
    }

    public static void main(@NotNull final String... args) {
        final LocalLevel levelBefore = LevelDataDecryption.decryptLevel(new File("CCLocalLevels1.dat"), 0);
        final LocalLevel levelAfter = LevelDataDecryption.decryptLevel(new File("CCLocalLevels2.dat"), 0);

        final HRMCommit commit = new HRMCommit(levelBefore, levelAfter);
        System.out.println(HRMCommit.decodeCommit(commit.encodeCommit()));
        System.out.println(commit);
    }


}