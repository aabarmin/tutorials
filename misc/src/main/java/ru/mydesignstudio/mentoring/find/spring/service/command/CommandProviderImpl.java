package ru.mydesignstudio.mentoring.find.spring.service.command;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommand;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandType;

import java.util.Map;

@Component
public class CommandProviderImpl implements CommandProvider {
    private final Map<FileCommandType, FileCommand> commands;

    public CommandProviderImpl(Map<FileCommandType, FileCommand> commands) {
        this.commands = commands;
    }

    @Override
    public FileCommand provide(FileCommandType type) {
        final FileCommand command = commands.get(type);
        if (command == null) {
            throw new IllegalArgumentException("There is no command with type " + type);
        }
        return command;
    }
}
