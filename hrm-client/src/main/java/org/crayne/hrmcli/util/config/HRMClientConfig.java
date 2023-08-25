package org.crayne.hrmcli.util.config;

import org.apache.commons.lang3.SystemUtils;
import org.crayne.hrm.repository.HRMRepository;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HRMClientConfig {

    @NotNull
    public static final File CONFIG_FILE = new File(System.getProperty("user.home"), "hrm-config.json");

    private boolean disableAnsi;

    @NotNull
    private String appdataPath;

    @NotNull
    private static final String UNSET_APPDATA_PATH = ": set your AppData file path here";

    public HRMClientConfig() {
        this.appdataPath = SystemUtils.IS_OS_WINDOWS ? System.getenv("APPDATA") : UNSET_APPDATA_PATH;
        this.disableAnsi = false;
    }

    @NotNull
    public static HRMClientConfig load() throws IOException {
        final HRMClientConfig config = !CONFIG_FILE.isFile() ? new HRMClientConfig()
                : HRMRepository.HRM_REPOSITORY_GSON.fromJson(Files.readString(CONFIG_FILE.toPath()), HRMClientConfig.class);

        try {
            config.save();
        } catch (final IOException e) {
            throw new HRMConfigException(e);
        }
        return config;
    }

    public void save() throws IOException {
        Files.writeString(CONFIG_FILE.toPath(), HRMRepository.HRM_REPOSITORY_GSON.toJson(this));
    }

    public boolean disableAnsi() {
        return disableAnsi;
    }

    public void disableAnsi(final boolean disableAnsi) {
        this.disableAnsi = disableAnsi;
    }

    @NotNull
    public String appdataPath() {
        return appdataPath;
    }

    public void appdataPath(@NotNull final String appdataPath) {
        this.appdataPath = appdataPath;
    }

    public boolean appdataDirectoryUnset() {
        return appdataPath.equals(UNSET_APPDATA_PATH);
    }

    @NotNull
    public File appdataDirectory() {
        if (appdataDirectoryUnset()) throw new HRMConfigException("AppData directory was not set");
        return new File(appdataPath);
    }

}
