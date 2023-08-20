package org.crayne.hrm.repository.commit;

import com.google.gson.reflect.TypeToken;
import org.crayne.hrm.repository.HRMRepository;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HRMCommitHistory {

    @NotNull
    private final List<HRMCommitInfo> commitInfos;

    public HRMCommitHistory(@NotNull final Collection<HRMCommitInfo> infos) {
        commitInfos = new ArrayList<>(infos);
    }

    public HRMCommitHistory() {
        commitInfos = new ArrayList<>();
    }

    @NotNull
    public static HRMCommitHistory readJson(@NotNull final File jsonFile) throws IOException {
        final String jsonString = Files.readString(jsonFile.toPath());
        final List<HRMCommitInfo> commitInfos = HRMRepository.HRM_REPOSITORY_GSON.fromJson(jsonString, new TypeToken<List<HRMCommitInfo>>(){}.getType());
        return new HRMCommitHistory(commitInfos == null ? new ArrayList<>() : commitInfos);
    }

    public void writeJson(@NotNull final File jsonFile) throws IOException {
        final String jsonString = HRMRepository.HRM_REPOSITORY_GSON.toJson(commitInfos);
        Files.writeString(jsonFile.toPath(), jsonString);
    }

    @NotNull
    public List<HRMCommitInfo> commitInfos() {
        return commitInfos;
    }

}
