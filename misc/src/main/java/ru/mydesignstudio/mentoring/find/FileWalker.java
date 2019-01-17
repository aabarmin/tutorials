package ru.mydesignstudio.mentoring.find;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class FileWalker {
    private final AtomicReference<String> currentFilename = new AtomicReference<>("");

    public void walk(File current, FileVisitor visitor) {
        final ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        final ForkJoinPool joinPool = ForkJoinPool.commonPool();
        try {
            pool.scheduleWithFixedDelay(new FilenameOutputter(), 1, 500, TimeUnit.MILLISECONDS);
            final WalkingAction firstTask = new WalkingAction(current, visitor);
            joinPool.execute(firstTask);
            firstTask.join();
        } finally {
            pool.shutdown();
            joinPool.shutdown();
        }
    }

    private class WalkingAction extends RecursiveAction {
        private final File current;
        private final FileVisitor visitor;

        public WalkingAction(File current, FileVisitor visitor) {
            this.current = current;
            this.visitor = visitor;
        }

        @Override
        protected void compute() {
            if (current.isFile() && !current.isHidden()) {
                visitor.visit(current);
                currentFilename.set(current.getName());
            } else {
                ForkJoinTask.invokeAll(createTasks(current, visitor));
            }
        }

        private Collection<WalkingAction> createTasks(File current, FileVisitor visitor) {
            final Collection<WalkingAction> actions = new ArrayList<>();
            final File[] files = current.listFiles();
            if (files != null) {
                for (File file : files) {
                    actions.add(new WalkingAction(file, visitor));
                }
            }
            return actions;
        }
    }

    private class FilenameOutputter implements Runnable {
        @Override
        public void run() {
            System.out.println("Current file is " + currentFilename.get());
        }
    }
}
