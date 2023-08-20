package org.crayne.hrm;

import org.crayne.hrm.repository.HRMRepository;
import org.crayne.hrm.repository.commit.HRMCommit;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    @NotNull
    private static final String MY_OWN_GD_APPDATA_FOLDER_CONFIG_LATER = "/home/crayne/gd/Geometry Dash Board/GD-Savefile-Test/drive_c/users/crayne/AppData/Local/GeometryDash";

    public static void main(@NotNull final String... args) throws IOException {
        final HRMRepository repository = new HRMRepository("hrm test i guess", "HRMCollab", new File(MY_OWN_GD_APPDATA_FOLDER_CONFIG_LATER), new File("HRMCollab"));
        repository.loadCurrentRepositoryProgress();

        final HRMCommit localCommit = repository.createLocalProgressCommit();
        if (localCommit.changes() == 0) {
            System.out.println("no changes in commit were found, aborting commit");
            System.exit(1);
            return;
        }

        System.out.println(localCommit.additions() + " additions, " + localCommit.changes() + " changes, " + localCommit.deletions() + " deletions");
        System.out.println("specify a commit message");

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String message = reader.readLine();
        System.out.println("committing...");

        repository.commit(localCommit, message);
        System.out.println("committing progress finished");
    }


}