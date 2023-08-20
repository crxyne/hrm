package org.crayne.hrm.api.level.data.settings.music;

import org.crayne.hrm.api.savefile.property.Properties;
import org.crayne.hrm.api.savefile.property.data.LevelObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class LevelMusicSettings {

    @NotNull
    private final Set<Guideline> guidelines;

    private boolean musicFadeIn, musicFadeOut;
    private float songOffset;

    public LevelMusicSettings(@NotNull final Set<Guideline> guidelines, final boolean musicFadeIn, final boolean musicFadeOut, final float songOffset) {
        this.guidelines = guidelines;
        this.musicFadeIn = musicFadeIn;
        this.musicFadeOut = musicFadeOut;
        this.songOffset = songOffset;
    }

    public LevelMusicSettings() {
        this.guidelines = new HashSet<>();
        this.musicFadeIn = false;
        this.musicFadeOut = false;
        this.songOffset = 0.0f;
    }

    public LevelMusicSettings(@NotNull final Properties levelSettings) {
        this.songOffset   = levelSettings.floatProperty(LevelObjectProperty.LEVEL_SONG_OFFSET);
        this.guidelines   = Guideline.ofGuidelineString(levelSettings.stringProperty(LevelObjectProperty.LEVEL_GUIDELINE_STRING));
        this.musicFadeIn  = levelSettings.booleanProperty(LevelObjectProperty.LEVEL_MUSIC_FADE_IN);
        this.musicFadeOut = levelSettings.booleanProperty(LevelObjectProperty.LEVEL_MUSIC_FADE_OUT);
    }

    @NotNull
    public Properties createProperties() {
        final Properties properties = new Properties(new HashMap<>());
        properties.putFloatProperty(LevelObjectProperty.LEVEL_SONG_OFFSET, songOffset);
        properties.putStringProperty(LevelObjectProperty.LEVEL_GUIDELINE_STRING, Guideline.toGuidelineString(guidelines));
        properties.putBooleanProperty(LevelObjectProperty.LEVEL_MUSIC_FADE_IN, musicFadeIn);
        properties.putBooleanProperty(LevelObjectProperty.LEVEL_MUSIC_FADE_OUT, musicFadeOut);

        return properties;
    }

    @NotNull
    public Set<Guideline> guidelines() {
        return guidelines;
    }

    public boolean musicFadeIn() {
        return musicFadeIn;
    }

    public void musicFadeIn(final boolean musicFadeIn) {
        this.musicFadeIn = musicFadeIn;
    }

    public boolean musicFadeOut() {
        return musicFadeOut;
    }

    public void musicFadeOut(final boolean musicFadeOut) {
        this.musicFadeOut = musicFadeOut;
    }

    public float songOffset() {
        return songOffset;
    }

    public void songOffset(final float songOffset) {
        this.songOffset = songOffset;
    }

    @NotNull
    public String toString() {
        return "LevelMusicSettings{" +
                "guidelines=" + guidelines +
                ", musicFadeIn=" + musicFadeIn +
                ", musicFadeOut=" + musicFadeOut +
                ", songOffset=" + songOffset +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LevelMusicSettings that = (LevelMusicSettings) o;

        if (musicFadeIn != that.musicFadeIn) return false;
        if (musicFadeOut != that.musicFadeOut) return false;
        if (Float.compare(that.songOffset, songOffset) != 0) return false;
        return guidelines.equals(that.guidelines);
    }

    public int hashCode() {
        int result = guidelines.hashCode();
        result = 31 * result + (musicFadeIn ? 1 : 0);
        result = 31 * result + (musicFadeOut ? 1 : 0);
        result = 31 * result + (songOffset != 0.0f ? Float.floatToIntBits(songOffset) : 0);
        return result;
    }
}
