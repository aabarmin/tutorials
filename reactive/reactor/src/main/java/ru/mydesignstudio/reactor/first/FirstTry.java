package ru.mydesignstudio.reactor.first;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FirstTry {
    public Mono<String> emptyMono() {
        return Mono.empty();
    }

    public Mono<String> fromString() {
        return Mono.just("Some value");
    }

    public Flux<String> emptyFlux() {
        return Flux.empty();
    }

    public Flux<String> fromStringArray() {
        return Flux.just(
                "first", "second", "third"
        );
    }

    public Flux<Integer> fromGenerator() {
        return Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next(state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                }
        );
    }
}
