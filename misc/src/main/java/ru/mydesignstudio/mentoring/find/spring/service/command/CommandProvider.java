package ru.mydesignstudio.mentoring.find.spring.service.command;

import ru.mydesignstudio.mentoring.find.spring.command.FileCommandType;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommand;

public interface CommandProvider {
    FileCommand provide(FileCommandType type);
}
