package org.crayne.gdboard.level.data.settings;

import org.crayne.gdboard.savefile.property.Properties;
import org.crayne.gdboard.savefile.property.PropertyUtil;
import org.crayne.gdboard.level.data.color.ColorProperty;
import org.crayne.gdboard.level.data.settings.music.LevelMusicSettings;
import org.crayne.gdboard.level.data.settings.start.LevelStartSettings;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class LevelSettings {

    private boolean twoPlayerMode;

    @NotNull
    private final Set<ColorProperty> levelColorProperties;

    @NotNull
    private final LevelStartSettings startObject;

    @NotNull
    private final LevelMusicSettings musicSettings;

    @NotNull
    private final LevelCosmetics levelCosmetics;

    public LevelSettings(@NotNull final LevelStartSettings startObject, final boolean twoPlayerMode,
                         @NotNull final LevelMusicSettings musicSettings, @NotNull final LevelCosmetics levelCosmetics,
                         @NotNull final Set<ColorProperty> levelColorProperties) {

        this.twoPlayerMode = twoPlayerMode;
        this.startObject = startObject;
        this.musicSettings = musicSettings;
        this.levelCosmetics = levelCosmetics;
        this.levelColorProperties = levelColorProperties;
    }

    public LevelSettings(@NotNull final String levelSettingsString) {
        final Map<String, String> levelSettings = PropertyUtil.decodeProperties(levelSettingsString, ",");
        final Properties levelSettingsProperties = new Properties(levelSettings);

        this.twoPlayerMode = PropertyUtil.parseBooleanValue(levelSettings.get("kA10"));
        this.startObject = new LevelStartSettings(levelSettingsProperties);
        this.musicSettings = new LevelMusicSettings(levelSettings);
        this.levelCosmetics = new LevelCosmetics(levelSettings);

        this.levelColorProperties = Optional.ofNullable(levelSettings.get("kS38"))
                .map(colorString -> Arrays.stream(colorString.split("\\|"))
                        .map(ColorProperty::new)
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    public boolean twoPlayerMode() {
        return twoPlayerMode;
    }

    public void twoPlayerMode(final boolean twoPlayerMode) {
        this.twoPlayerMode = twoPlayerMode;
    }

    @NotNull
    public Set<ColorProperty> levelColorProperties() {
        return levelColorProperties;
    }

    @NotNull
    public LevelStartSettings startObject() {
        return startObject;
    }

    @NotNull
    public LevelMusicSettings musicSettings() {
        return musicSettings;
    }

    @NotNull
    public LevelCosmetics levelCosmetics() {
        return levelCosmetics;
    }

    @NotNull
    public String toString() {
        return "LevelSettings{" +
                "twoPlayerMode=" + twoPlayerMode +
                ", levelColorProperties=" + levelColorProperties +
                ", startObject=" + startObject +
                ", musicSettings=" + musicSettings +
                ", levelCosmetics=" + levelCosmetics +
                '}';
    }

}
