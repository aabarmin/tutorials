package ru.mydesignstudio.mentoring.find.spring.command.impl;

import ru.mydesignstudio.mentoring.find.spring.command.FileCommandResult;

public class FindFileWithMaximumSCountResult implements FileCommandResult {
    private String filename = "";

    public String getFilename() {
        return filename;
    }

    public void nextFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return getFilename();
    }
}
