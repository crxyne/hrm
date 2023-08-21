package org.crayne.hrm.repository.commit;

import org.crayne.hrm.api.level.LocalLevel;
import org.crayne.hrm.api.level.data.color.ColorProperty;
import org.crayne.hrm.api.level.data.object.type.LevelObject;
import org.crayne.hrm.api.level.data.settings.LevelSettings;
import org.crayne.hrm.api.savefile.decrypt.LevelDataDecryption;
import org.crayne.hrm.api.savefile.encrypt.LevelDataEncryption;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@SuppressWarnings("unused")
public class HRMCommit {

    @NotNull
    private final List<LevelObject> addedObjects;

    @NotNull
    private final List<LevelObject> removedObjects;

    @NotNull
    private final Set<ColorProperty> addedColorProperties;

    @NotNull
    private final Set<ColorProperty> removedColorProperties;

    @NotNull
    private final Set<ColorProperty> modifiedColorProperties;

    private final int changes;
    private final int additions;
    private final int deletions;

    public HRMCommit(@NotNull final LocalLevel commitProgress, @NotNull final LocalLevel currentLevel) {
        this(commitProgress.data().levelObjects(),
                currentLevel.data().levelObjects(),
                commitProgress.data().levelSettings().levelColorProperties(),
                currentLevel.data().levelSettings().levelColorProperties());
    }

    private HRMCommit(@NotNull final Collection<LevelObject> commitLevelObjects, @NotNull final Collection<LevelObject> currentLevelObjects,
                      @NotNull final Set<ColorProperty> commitColorProperties, @NotNull final Set<ColorProperty> currentColorPropeties) {
        this.removedObjects = new ArrayList<>(commitLevelObjects);
        removedObjects.removeAll(currentLevelObjects);

        this.removedColorProperties = new HashSet<>(commitColorProperties);
        removedColorProperties.removeAll(currentColorPropeties);

        this.addedObjects = new ArrayList<>(commitLevelObjects);
        addedObjects.removeAll(currentLevelObjects);

        this.addedColorProperties = new HashSet<>(commitColorProperties);
        addedColorProperties.removeAll(currentColorPropeties);

        final Map<ColorProperty, ColorProperty> modifiedColorPropertiesMap = new HashMap<>();

        addedColorProperties.forEach(newColorProperty -> removedColorProperties.forEach(oldColorProperty -> {
            if (newColorProperty.channelIndex() != oldColorProperty.channelIndex()) return;
            modifiedColorPropertiesMap.put(oldColorProperty, newColorProperty);
        }));

        removedColorProperties.removeAll(modifiedColorPropertiesMap.keySet());
        addedColorProperties.removeAll(modifiedColorPropertiesMap.values());
        modifiedColorProperties = new HashSet<>(modifiedColorPropertiesMap.values());

        this.modifiedColorProperties.removeIf(c -> c.channelIndex() == 0);
        this.removedColorProperties.removeIf(c -> c.channelIndex() == 0);
        this.addedColorProperties.removeIf(c -> c.channelIndex() == 0);

        changes = removedObjects.size() + addedObjects.size() + removedColorProperties.size() + addedColorProperties.size() + modifiedColorProperties.size();
        additions = addedObjects.size() + addedColorProperties.size();
        deletions = removedObjects.size() + removedColorProperties.size();
    }

    private HRMCommit(@NotNull final List<LevelObject> removedObjects, @NotNull final Set<ColorProperty> removedColorProperties,
                      @NotNull final List<LevelObject> addedObjects, @NotNull final Set<ColorProperty> addedColorProperties,
                      @NotNull final Set<ColorProperty> modifiedColorProperties) {
        this.removedObjects = removedObjects;
        this.removedColorProperties = removedColorProperties;
        this.addedObjects = addedObjects;
        this.addedColorProperties = addedColorProperties;
        this.modifiedColorProperties = modifiedColorProperties;

        this.modifiedColorProperties.removeIf(c -> c.channelIndex() == 0);
        this.removedColorProperties.removeIf(c -> c.channelIndex() == 0);
        this.addedColorProperties.removeIf(c -> c.channelIndex() == 0);

        changes = removedObjects.size() + addedObjects.size() + removedColorProperties.size() + addedColorProperties.size() + modifiedColorProperties.size();
        additions = addedObjects.size() + addedColorProperties.size();
        deletions = removedObjects.size() + removedColorProperties.size();
    }

    @NotNull
    public String encodeCommit() {
        return "-" + String.join(";", LevelDataEncryption.encryptLevelObjects(removedObjects, false)) + "\n"
                + "--" + LevelSettings.createColorString(removedColorProperties, false) + "\n"
                + "+" + String.join(";", LevelDataEncryption.encryptLevelObjects(addedObjects, false)) + "\n"
                + "++" + LevelSettings.createColorString(addedColorProperties, false) + "\n"
                + "~~" + LevelSettings.createColorString(modifiedColorProperties, false) + "\n";
    }

    @NotNull
    private static String findCommitProperty(@NotNull final Collection<String> split, @NotNull final String prefix) {
        return split.stream().filter(s -> s.startsWith(prefix)).findAny().orElseThrow(HRMCommitDecodeException::new).substring(prefix.length());
    }

    @NotNull
    public static HRMCommit decodeCommit(@NotNull final String encodedCommit) {
        final List<String> split = Arrays.stream(encodedCommit.split("\n")).filter(s -> !s.isBlank()).toList();
        if (split.size() != 5) throw new HRMCommitDecodeException("Invalid commit string: expected 5 properties, but got " + split.size());

        final String removedObjectsStr = findCommitProperty(split, "-");
        final String removedColorPropertiesStr = findCommitProperty(split, "--");
        final String addedObjectsStr = findCommitProperty(split, "+");
        final String addedColorPropertiesStr = findCommitProperty(split, "++");
        final String modifiedColorPropertiesStr = findCommitProperty(split, "~~");

        return new HRMCommit(
                LevelDataDecryption.decryptLevelObjects(removedObjectsStr.split(";"), false),
                LevelSettings.parseColorString(removedColorPropertiesStr),
                LevelDataDecryption.decryptLevelObjects(addedObjectsStr.split(";"), false),
                LevelSettings.parseColorString(addedColorPropertiesStr),
                LevelSettings.parseColorString(modifiedColorPropertiesStr)
        );
    }

    @NotNull
    public static HRMCommit mergeCommits(@NotNull final Collection<HRMCommit> commits, @NotNull final LocalLevel currentProgress) {
        final LocalLevel initialProgressNoCommits = currentProgress.createSettingsOnlyLevel();
        final HRMCommit initialProgressCommit = new HRMCommit(initialProgressNoCommits, currentProgress);
        initialProgressCommit.applyChanges(initialProgressNoCommits);

        commits.forEach(commit -> commit.applyChanges(initialProgressNoCommits));
        return new HRMCommit(initialProgressNoCommits, currentProgress);
    }

    public void applyChanges(@NotNull final LocalLevel level) {
        level.removeAllLevelObjects(removedObjects);
        level.data().levelSettings().removeAllColorProperties(removedColorProperties);
        level.addLevelObjects(addedObjects);
        level.data().levelSettings().addAllColorProperties(addedColorProperties);
        level.data().levelSettings().addAllColorProperties(modifiedColorProperties);
    }

    public int changes() {
        return changes;
    }

    public int additions() {
        return additions;
    }

    public int deletions() {
        return deletions;
    }

    @NotNull
    public Set<ColorProperty> addedColorProperties() {
        return Collections.unmodifiableSet(addedColorProperties);
    }

    @NotNull
    public Set<ColorProperty> removedColorProperties() {
        return Collections.unmodifiableSet(removedColorProperties);
    }

    @NotNull
    public Set<ColorProperty> modifiedColorProperties() {
        return Collections.unmodifiableSet(modifiedColorProperties);
    }

    @NotNull
    public List<LevelObject> addedObjects() {
        return Collections.unmodifiableList(addedObjects);
    }

    @NotNull
    public List<LevelObject> removedObjects() {
        return Collections.unmodifiableList(removedObjects);
    }

    @NotNull
    public String toString() {
        return "HRMCommit{" +
                "addedObjects=" + addedObjects +
                ", removedObjects=" + removedObjects +
                ", addedColorProperties=" + addedColorProperties +
                ", removedColorProperties=" + removedColorProperties +
                ", modifiedColorProperties=" + modifiedColorProperties +
                ", changes=" + changes +
                ", additions=" + additions +
                ", deletions=" + deletions +
                '}';
    }

    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final HRMCommit commit = (HRMCommit) o;

        if (changes != commit.changes) return false;
        if (additions != commit.additions) return false;
        if (deletions != commit.deletions) return false;
        if (!addedObjects.equals(commit.addedObjects)) return false;
        if (!removedObjects.equals(commit.removedObjects)) return false;
        if (!addedColorProperties.equals(commit.addedColorProperties)) return false;
        if (!removedColorProperties.equals(commit.removedColorProperties)) return false;
        return modifiedColorProperties.equals(commit.modifiedColorProperties);
    }

    public int hashCode() {
        int result = addedObjects.hashCode();
        result = 31 * result + removedObjects.hashCode();
        result = 31 * result + addedColorProperties.hashCode();
        result = 31 * result + removedColorProperties.hashCode();
        result = 31 * result + modifiedColorProperties.hashCode();
        result = 31 * result + changes;
        result = 31 * result + additions;
        result = 31 * result + deletions;
        return result;
    }
}
