package ru.mydesignstudio.mentoring.find.spring.service.app;

import ru.mydesignstudio.mentoring.find.spring.command.FileCommandResult;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandType;

import java.io.File;

public interface AnalyzerApp {
    FileCommandResult runExecution(FileCommandType type, File start);
}
