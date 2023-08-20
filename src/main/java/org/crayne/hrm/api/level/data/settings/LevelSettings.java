package org.crayne.hrm.api.level.data.settings;

import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.PropertyUtil;
import org.crayne.hrm.api.level.data.color.ColorProperty;
import org.crayne.hrm.api.level.data.settings.music.LevelMusicSettings;
import org.crayne.hrm.api.level.data.settings.start.LevelStartSettings;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
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

    public LevelSettings() {
        this.twoPlayerMode = false;
        this.startObject = new LevelStartSettings();
        this.musicSettings = new LevelMusicSettings();
        this.levelCosmetics = new LevelCosmetics();
        this.levelColorProperties = new HashSet<>();
    }

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
        final Properties properties = new Properties(PropertyUtil.decodeProperties(levelSettingsString, ","));

        this.twoPlayerMode = properties.booleanProperty(LevelObjectProperty.LEVEL_IS_TWO_PLAYER_MODE);
        this.startObject = new LevelStartSettings(properties);
        this.musicSettings = new LevelMusicSettings(properties);
        this.levelCosmetics = new LevelCosmetics(properties);

        this.levelColorProperties = parseColorString(properties.stringProperty(LevelObjectProperty.LEVEL_COLOR_STRING));
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = new Properties(new HashMap<>());

        properties.putBooleanProperty(LevelObjectProperty.LEVEL_IS_TWO_PLAYER_MODE, twoPlayerMode);
        properties.putAll(startObject.createProperties());
        properties.putAll(musicSettings.createProperties());
        properties.putAll(levelCosmetics.createProperties());
        properties.putStringProperty(LevelObjectProperty.LEVEL_COLOR_STRING, createColorString(levelColorProperties, true));

        return properties;
    }

    @NotNull
    public static Set<ColorProperty> parseColorString(@NotNull final String colorString) {
        return Arrays.stream(colorString.split("\\|"))
                .map(ColorProperty::new)
                .collect(Collectors.toSet());
    }

    @NotNull
    public static String createColorString(@NotNull final Collection<ColorProperty> levelColorProperties, final boolean addLastDelimiter) {
        return levelColorProperties.stream()
                .map(ColorProperty::createColorString)
                .filter(s -> !s.isBlank())
                .collect(Collectors.joining("|")) + (addLastDelimiter ? "|" : "");
    }

    @NotNull
    public String createLevelSettingsString() {
        return PropertyUtil.encodeProperties(createProperties().propertiesMap(), ",");
    }

    public boolean twoPlayerMode() {
        return twoPlayerMode;
    }

    public void twoPlayerMode(final boolean twoPlayerMode) {
        this.twoPlayerMode = twoPlayerMode;
    }

    @NotNull
    public Set<ColorProperty> levelColorProperties() {
        return Collections.unmodifiableSet(levelColorProperties);
    }

    public void addColorProperty(@NotNull final ColorProperty colorProperty) {
        if (colorProperty.channelIndex() != 0) levelColorProperties.removeIf(c -> c.channelIndex() == colorProperty.channelIndex());
        levelColorProperties.add(colorProperty);
    }

    public void removeColorProperty(final int channelIndex) {
        levelColorProperties.removeIf(c -> c.channelIndex() == channelIndex);
    }

    public void removeColorProperty(@NotNull final Predicate<ColorProperty> removeIf) {
        levelColorProperties.removeIf(removeIf);
    }

    public void removeColorProperty(@NotNull final ColorProperty colorProperty) {
        levelColorProperties.remove(colorProperty);
    }

    @NotNull
    public Optional<ColorProperty> colorProperty(final int channelIndex) {
        return levelColorProperties.stream().filter(c -> c.channelIndex() == channelIndex).findAny();
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

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LevelSettings settings = (LevelSettings) o;

        if (twoPlayerMode != settings.twoPlayerMode) return false;
        if (!levelColorProperties.equals(settings.levelColorProperties)) return false;
        if (!startObject.equals(settings.startObject)) return false;
        if (!musicSettings.equals(settings.musicSettings)) return false;
        return levelCosmetics.equals(settings.levelCosmetics);
    }

    public int hashCode() {
        int result = (twoPlayerMode ? 1 : 0);
        result = 31 * result + levelColorProperties.hashCode();
        result = 31 * result + startObject.hashCode();
        result = 31 * result + musicSettings.hashCode();
        result = 31 * result + levelCosmetics.hashCode();
        return result;
    }
}
