package org.crayne.gdboard.level.data.settings;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

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
}
