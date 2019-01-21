package ru.mydesignstudio.mentoring.find.spring.command.impl;

import ru.mydesignstudio.mentoring.find.FileVisitor;

import java.io.File;

public class FindFileWithMaximumSCountVisitor implements FileVisitor {
    private final FindFileWithMaximumSCountResult result = new FindFileWithMaximumSCountResult();

    @Override
    public void visit(File file) {
        if (getCount(file) > getCount(result.getFilename())) {
            result.nextFilename(file.getName());
        }
    }

    private int getCount(String name) {
        int count = 0;
        int index = -1;
        while (name.indexOf('s', index + 1) != -1) {
            count++;
            index = name.indexOf('s', index + 1);
        }
        return count;
    }

    private int getCount(File file) {
        return getCount(file.getName());
    }

    public FindFileWithMaximumSCountResult getResult() {
        return result;
    }
}
