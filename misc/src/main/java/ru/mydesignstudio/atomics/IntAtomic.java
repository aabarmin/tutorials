package ru.mydesignstudio.atomics;

import java.util.concurrent.atomic.AtomicInteger;

public class IntAtomic {
    private AtomicInteger value = new AtomicInteger(0);

    public int getValue() {
        return value.get();
    }

    public void increment() {
        value.incrementAndGet();
    }
}
