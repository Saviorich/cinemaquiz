package by.bntu.fitr.cinemaquiz.controller.command;

import java.util.EnumMap;
import java.util.Map;

public final class CommandProvider {
    private static final CommandProvider INSTANCE = new CommandProvider();

    private final Map<CommandName, Command> commands = new EnumMap<>(CommandName.class);

    private CommandProvider() {
        commands.put(CommandName.GOTOMAINPAGE, new GoToMainPageCommand());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    public Command takeCommand(String command) {
        return commands.get(CommandName.valueOf(command.toUpperCase()));
    }
}
