package org.crayne.hrmcli;

import org.crayne.hrm.console.log.HRMLogger;
import org.crayne.hrm.repository.HRMRepository;
import org.crayne.hrm.repository.HRMRepositoryException;
import org.crayne.hrm.repository.HRMRepositoryInfo;
import org.crayne.hrm.repository.commit.HRMCommit;
import org.crayne.hrm.repository.commit.HRMCommitInfo;
import org.crayne.hrmcli.util.config.HRMClientConfig;
import org.crayne.hrmcli.util.config.HRMConfigException;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class HRMClient {

    @NotNull
    private final HRMLogger logger;

    @NotNull
    private final HRMClientConfig config;

    public HRMClient(@NotNull final HRMLogger logger) {
        this.logger = logger;
        try {
            this.config = HRMClientConfig.load();
        } catch (final IOException e) {
            throw new HRMConfigException(e);
        }
    }

    public HRMClient() {
        try {
            this.config = HRMClientConfig.load();
        } catch (final IOException e) {
            throw new HRMConfigException(e);
        }
        this.logger = new HRMLogger(!this.config.disableAnsi());
    }

    public void printWelcomePage() {
        logger.info("welcome to hrm - a collab client for making GD levels together");
        logger.info("to make a new project, use the 'new' argument like so: hrm new \"project name\" \"ingame level name\"");
    }

    public void runCommand(@NotNull final String[] commandLineArgs) {
        final List<String> args = Arrays.stream(commandLineArgs)
                .filter(s -> !s.startsWith("-"))
                .toList();

        final List<String> params = Arrays.stream(commandLineArgs)
                .filter(s -> s.startsWith("-"))
                .map(s -> s.substring(1))
                .filter(s -> !s.isBlank()).toList();

        if (args.isEmpty()) {
            printWelcomePage();
            return;
        }
        final List<String> otherArgs = args.subList(1, args.size());
        final String option = args.get(0);

        switch (option) {
            case "new" -> createNewProject(otherArgs, params);
            case "appdata" -> updateAppdataPath(otherArgs);
            case "ansi" -> toggleAnsi(otherArgs);
            case "commit" -> commitProgress(otherArgs);
            case "mynameis" -> updateUsername(otherArgs);
        }
    }

    @NotNull
    private static Optional<String> findParameterValue(@NotNull final List<String> params, @NotNull final String key) {
        return params.stream()
                .filter(p -> p.startsWith(key + "="))
                .map(s -> s.substring(key.length() + 1))
                .filter(s -> !s.isBlank())
                .findAny();
    }

    private static final Pattern VALID_PROJECT_NAME_REGEX = Pattern.compile("[-_.A-Za-z0-9]*");

    private boolean trySaveConfig() {
        try {
            config.save();
        } catch (final IOException e) {
            logger.error("could not save config file: " + e.getMessage());
            return true;
        }
        return false;
    }

    public void toggleAnsi(@NotNull final List<String> args) {
        if (args.size() != 1) {
            logger.error("could not change ansi usage settings - invalid arguments provided");
            logger.info("usage: hrm ansi true; hrm ansi false");
            return;
        }
        final boolean enable = Boolean.parseBoolean(args.get(0));
        config.disableAnsi(!enable);
        if (trySaveConfig()) return;
        logger.success((enable ? "enabled" : "disabled") + " ansi escape sequences");
    }

    public void updateAppdataPath(@NotNull final List<String> args) {
        if (args.size() != 1) {
            logger.error("could not change appdata path - invalid arguments provided");
            logger.info("usage: hrm appdata \"full/filepath/to/AppData/Local/GeometryDash\"");
            return;
        }
        final String appdataPath = args.get(0);
        final File appdataDirectory = new File(appdataPath);
        if (!appdataDirectory.isDirectory()) {
            logger.error("could not change appdata path - directory was not found");
            return;
        }
        config.appdataPath(appdataPath);
        if (trySaveConfig()) return;
        logger.success("updated appdata path to: " + appdataDirectory.getAbsolutePath());
    }

    public void updateUsername(@NotNull final List<String> args) {
        if (args.size() != 1) {
            logger.error("could not change local author name - invalid arguments provided");
            logger.info("usage: hrm mynameis \"what you would like to call yourself\"");
            return;
        }
        final String authorName = args.get(0);
        config.localAuthorName(authorName);
        if (trySaveConfig()) return;
        logger.success("updated local author name to: " + authorName);
    }

    public void createNewProject(@NotNull final List<String> args, @NotNull final List<String> params) {
        if (args.size() != 2 && args.size() != 3) {
            logger.error("could not create new project - invalid arguments provided");
            logger.info("to make a new project, use the 'new' argument like so: hrm new \"project name\" \"ingame level name\"");
            logger.info("if you want to create the project in a specific directory, add the file path at the end of the arguments.");
            return;
        }
        final Optional<String> appdataParameter = findParameterValue(params, "appdata");
        if (config.appdataDirectoryUnset() && appdataParameter.isEmpty()) {
            logger.error("could not create new project - appdata directory is not set in config");
            logger.info("set your appdata directory in the config at " + HRMClientConfig.CONFIG_FILE.getAbsolutePath());
            logger.info("or use the appdata command to set your default appdata directory, usage: hrm appdata \"full/filepath/here\"");
            logger.info("or to set a custom appdata for one project only, add the parameter -appdata=\"full/filepath/here\"");
            return;
        }
        final String projectName = args.get(0);
        if (!VALID_PROJECT_NAME_REGEX.matcher(projectName).matches() || projectName.length() == 0 || projectName.length() > 32) {
            logger.error("could not create new project - invalid project name '" + projectName + "'");
            logger.info("project names must be between 1 and 32 characters in length and can only contain alphanumeric characters, periods, dashes and underscores.");
            return;
        }
        final String levelName = args.get(1);
        final File projectDirectory = args.size() == 3 ? new File(args.get(2), projectName) : new File(projectName);
        final File appdataDirectory = appdataParameter.map(File::new).orElse(config.appdataDirectory());

        if (!appdataDirectory.isDirectory()) {
            logger.error("could not create new project - appdata directory was not found");
            return;
        }
        final HRMRepositoryInfo repositoryInfo = new HRMRepositoryInfo(projectName, levelName, appdataDirectory.getAbsolutePath());
        final HRMRepository repository = new HRMRepository(repositoryInfo, projectDirectory);

        try {
            final boolean alreadyInit = !repository.init();

            if (alreadyInit) {
                logger.error("could not create new project - project already initialized");
                return;
            }
        } catch (final HRMRepositoryException e) {
            logger.error("could not create new project - " + e.getMessage().toLowerCase());
            return;
        }
        logger.success("created new project named '" + projectName + "'");
    }

    public void commitProgress(@NotNull final List<String> args) {
        if (args.size() != 1) {
            logger.error("could not commit progress to repository - invalid arguments provided");
            logger.info("usage: hrm commit \"commit message here\"");
            return;
        }
        final String commitMessage = args.get(0);
        final String authorName = config.localAuthorName();
        final HRMCommitInfo commitInfo = new HRMCommitInfo(commitMessage, authorName);
        final HRMRepository repository = HRMRepository.readRepository(new File("").getAbsoluteFile());
        final HRMCommit commit = repository.createLocalProgressCommit();
        if (commit.changes() == 0) {
            logger.warn("no changes were found in commit, aborting");
            return;
        }

        logger.info("commit message:   " + commitMessage);
        logger.info("committed by:     " + authorName);
        logger.info("commit timestamp: " + commitInfo.timestampFormatted());
        logger.info("added objects:    " + commit.addedObjects().size());
        logger.info("removed objects:  " + commit.removedObjects().size());
        logger.info("added colors:     " + commit.addedColorProperties().size());
        logger.info("removed colors:   " + commit.removedColorProperties().size());
        logger.info("modified colors:  " + commit.modifiedColorProperties().size());
        logger.info("are you sure you want to commit this progress to repository '" + repository.repositoryInfo().name() + "'? (y/n)");

        final boolean commitConfirmed = awaitYesNo();
        if (!commitConfirmed) {
            logger.warn("commit aborted");
            return;
        }
        repository.commit(commit, commitInfo);
        logger.success("successfully committed to repository");
    }

    public boolean awaitYesNo() {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine().equalsIgnoreCase("y");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public HRMLogger logger() {
        return logger;
    }

}
