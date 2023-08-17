package org.crayne.gdboard.level.data.settings.music;

import org.crayne.gdboard.savefile.property.PropertyUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
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

    public LevelMusicSettings(@NotNull final Map<String, String> levelSettings) {
        this.songOffset   = PropertyUtil.parseFloatValue(levelSettings.get("kA13"), 0);
        this.guidelines   = Optional.ofNullable(levelSettings.get("kA14")).map(Guideline::ofGuidelineString).orElse(new HashSet<>());
        this.musicFadeIn  = PropertyUtil.parseBooleanValue(levelSettings.get("kA15"));
        this.musicFadeOut = PropertyUtil.parseBooleanValue(levelSettings.get("kA16"));
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

}
