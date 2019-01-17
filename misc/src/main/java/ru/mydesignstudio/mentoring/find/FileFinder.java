package ru.mydesignstudio.mentoring.find;

import java.io.File;
import java.util.concurrent.Callable;

public class FileFinder implements Callable<String> {
    @Override
    public String call() {
        final File userHome = new File(System.getProperty("user.home"));
        final LongestFilenameFinder finder = new LongestFilenameFinder();
        new FileWalker().walk(userHome, finder);
        return finder.longest;
    }

    private class LongestFilenameFinder implements FileVisitor {
        private String longest = "";

        @Override
        public void visit(File file) {
            if (file.getName().length() > longest.length()) {
                longest = file.getName();
            }
        }
    }
}
