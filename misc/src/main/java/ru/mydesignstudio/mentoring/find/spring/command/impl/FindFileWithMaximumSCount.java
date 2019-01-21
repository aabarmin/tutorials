package ru.mydesignstudio.mentoring.find.spring.command.impl;

import ru.mydesignstudio.mentoring.find.FileWalker;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommand;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandResult;

import java.io.File;

public class FindFileWithMaximumSCount implements FileCommand {
    @Override
    public FileCommandResult execute(File file) {
        final FindFileWithMaximumSCountVisitor visitor = new FindFileWithMaximumSCountVisitor();
        new FileWalker().walk(file, visitor);
        return visitor.getResult();
    }
}
