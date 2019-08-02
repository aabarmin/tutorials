package ru.mydesignstudio.reactor.first;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FirstTryWithStepVerifierTest {
    private FirstTry unitUnderTest;

    @BeforeEach
    void setup() {
        unitUnderTest = new FirstTry();
    }

    @Test
    void empty_toUseStepVerifier() {
        StepVerifier.create(unitUnderTest.emptyMono())
                .verifyComplete();
    }

    @Test
    void fromString_shouldBeEmitted() {
        StepVerifier.create(unitUnderTest.fromString())
                .expectNext("Some value")
                .verifyComplete();
    }
}
