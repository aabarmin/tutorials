package ru.mydesignstudio.mentoring.find;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

class FileFinderTest {
    private Callable<String> finder = new FileFinder();

    @Test
    @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
    void name() throws Exception {
        final String result = finder.call();
        assertNotNull(result);
    }
}