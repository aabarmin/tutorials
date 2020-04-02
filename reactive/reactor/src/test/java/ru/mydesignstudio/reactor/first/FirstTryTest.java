package ru.mydesignstudio.reactor.first;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FirstTryTest {
    FirstTry unitUnderTest = new FirstTry();

    @Test
    void mono_emptyMonoIsReturned() {
        final Mono<String> mono = unitUnderTest.emptyMono();

        assertAll(
                () -> assertNotNull(mono)
        );
    }

    @Test
    void mono_fromStringIsReturned() {
        final Mono<String> mono = unitUnderTest.fromString();

        assertAll(
                () -> assertNotNull(mono),
                () -> mono.subscribe(value -> assertNotNull(value)),
                () -> mono.subscribe(value -> assertEquals("Some value", value))
        );
    }

    @Test
    void flux_emptyFluxShouldBeReturned() {
        final Flux<String> flux = unitUnderTest.emptyFlux();

        assertAll(
                () -> assertNotNull(flux)
        );
    }

    @Test
    void flux_fromStringArray() {
        final Flux<String> flux = unitUnderTest.fromStringArray();

        assertAll(
                () -> assertNotNull(flux),
                () -> flux.subscribe(value -> assertNotNull(value))
        );
    }

    @Test
    void flux_tryToUseOperations() {
        unitUnderTest.fromStringArray()
                .map(value -> value.length())
                .subscribe(System.out::println);
    }

    @Test
    void flux_fromGenerator() {
        unitUnderTest.fromGenerator()
                .subscribe(System.out::println);
    }

    @Test
    void flux_withErrorHandler() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .map(i -> i - 5)
                .map(i -> 10 / i)
                .onErrorReturn(9999)
                .doFinally(i -> System.out.println("After all " + i))
                .subscribe(System.out::println);
    }
}
