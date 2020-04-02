package ru.mydesignstudio.spring.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ParallelExecutionTest.Config.class)
@Execution(ExecutionMode.CONCURRENT)
public class ParallelExecutionTest {
    @RepeatedTest(10)
    void first() {
        System.out.println("First");
    }

    @RepeatedTest(10)
    void second() {
        System.out.println("Second");
    }

    @Configurable
    static class Config {

    }
}
