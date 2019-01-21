package ru.mydesignstudio.mentoring.find.spring.command;

import java.io.File;

@FunctionalInterface
public interface FileCommand {
    FileCommandResult execute(File file);
}
