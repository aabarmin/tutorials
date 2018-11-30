package ru.mydesignstudio.atomics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class IntAtomicTest {
    private IntAtomic atomic;

    @BeforeEach
    void setUp() {
        atomic = new IntAtomic();
    }

    @Test
    void testSingleThread() {
        IntStream.range(0, 10).forEach(i -> atomic.increment());
        assertEquals(10, atomic.getValue());
    }

    @Test
    void testMultipleThreadSingleOperation() throws Exception {
        final CountDownLatch latch = new CountDownLatch(10);
        final List<Thread> threads =
                IntStream.range(0, 10)
                        .mapToObj(i -> (Runnable) () -> {
                            try {
                                latch.await();
                                atomic.increment();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .map(runnable -> new Thread(runnable))
                        .map(thread -> {
                            latch.countDown();
                            return thread;
                        })
                        .map(thread -> {
                            thread.start();
                            return thread;
                        })
                        .collect(Collectors.toList());
        for (Thread thread : threads) {
            thread.join();
        }
        assertEquals(10, atomic.getValue());
    }

    @Test
    void testMultipleThreadMultipleOperations() throws Exception {
        final CountDownLatch latch = new CountDownLatch(5);
        final List<Thread> threads =
                IntStream.range(0, 5)
                        .mapToObj(i -> (Runnable) () -> {
                            try {
                                latch.await();
                                atomic.increment();
                                atomic.increment();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .map(runnable -> new Thread(runnable))
                        .map(thread -> {
                            latch.countDown();
                            return thread;
                        })
                        .map(thread -> {
                            thread.start();
                            return thread;
                        })
                        .collect(Collectors.toList());
        for (Thread thread : threads) {
            thread.join();
        }
        assertEquals(10, atomic.getValue());
    }

    @Test
    void testThreadPool() throws Exception {
        final ExecutorService pool = Executors.newCachedThreadPool();
        IntStream.range(0, 10)
                .mapToObj(i -> (Runnable) () -> {
                    atomic.increment();
                })
                .forEach(task -> pool.submit(task));
        pool.awaitTermination(1, TimeUnit.SECONDS);
        assertEquals(10, atomic.getValue());
    }
}