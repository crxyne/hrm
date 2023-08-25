package org.crayne.hrmcli;

import org.crayne.hrm.console.log.HRMLogger;
import org.crayne.hrm.repository.HRMRepository;
import org.crayne.hrm.repository.HRMRepositoryException;
import org.crayne.hrmcli.util.config.HRMClientConfig;
import org.crayne.hrmcli.util.config.HRMConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class HRMClient {

    @NotNull
    private final HRMLogger logger;

    @Nullable
    private File appdataDirectory;

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

    public HRMClient(@NotNull final String[] commandLineArgs) {
        this(new HRMLogger(!Arrays.asList(commandLineArgs).contains("-noansi")));
    }

    public void printWelcomePage() {
        logger.info("welcome to hrm - a collab client for making GD levels together");
        logger.info("to make a new project, use the 'new' argument like so: hrm new \"project name\" \"ingame level name\"");
    }

    public void runCommand(@NotNull final String[] commandLineArgs) {
        final List<String> args = Arrays.stream(commandLineArgs).filter(s -> !s.startsWith("-")).toList();
        if (args.isEmpty()) {
            printWelcomePage();
            return;
        }
        final List<String> otherArgs = args.subList(1, args.size());
        final String option = args.get(0);

        switch (option) {
            case "new" -> createNewProject(otherArgs);
        }
    }

    private static final Pattern VALID_PROJECT_NAME_REGEX = Pattern.compile("[-_.A-Za-z0-9]*");

    public void createNewProject(@NotNull final List<String> args) {
        if (args.size() != 2 && args.size() != 3) {
            logger.error("could not create new project - invalid arguments provided");
            logger.info("to make a new project, use the 'new' argument like so: hrm new \"project name\" \"ingame level name\"");
            logger.info("if you want to create the project in a specific directory, add the file path at the end of the arguments.");
            return;
        }
        if (config.appdataDirectoryUnset()) {
            logger.error("could not create new project - appdata directory is not set in config");
            logger.info("set your appdata directory in the config at " + HRMClientConfig.CONFIG_FILE.getAbsolutePath());
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
        final File appdataDirectory = new File(config.appdataDirectory(), "Local/GeometryDash");
        final HRMRepository repository = new HRMRepository(projectName, levelName, appdataDirectory, projectDirectory);

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

    @NotNull
    public HRMLogger logger() {
        return logger;
    }

}
