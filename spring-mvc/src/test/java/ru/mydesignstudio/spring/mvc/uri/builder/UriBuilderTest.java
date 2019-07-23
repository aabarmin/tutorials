package ru.mydesignstudio.spring.mvc.uri.builder;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UriBuilderTest {
    @Test
    void check_howItWorks() throws Exception {
        final URI uri = UriComponentsBuilder.fromUriString("http://google.com/")
                .queryParam("param", "value")
                .build("some", "any");

        assertEquals("http://google.com/?param=value", uri.toURL().toExternalForm());
    }
}
