package ru.mydesignstudio.mentoring.find.spring.service.app;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommand;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandResult;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandType;
import ru.mydesignstudio.mentoring.find.spring.service.command.CommandProvider;

import java.io.File;

@Component
public class AnalyzerAppImpl implements AnalyzerApp {
    private final CommandProvider commandProvider;

    public AnalyzerAppImpl(CommandProvider commandProvider) {
        this.commandProvider = commandProvider;
    }

    @Override
    public FileCommandResult runExecution(FileCommandType type, File start) {
        final FileCommand command = commandProvider.provide(type);
        final FileCommandResult result = command.execute(start);
        return result;
    }
}
