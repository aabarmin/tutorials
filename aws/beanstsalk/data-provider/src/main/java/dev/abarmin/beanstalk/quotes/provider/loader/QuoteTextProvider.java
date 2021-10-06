package dev.abarmin.beanstalk.quotes.provider.loader;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class QuoteTextProvider {
    @Value("classpath:/MOCK_DATA.csv")
    private Resource dataResource;

    @SneakyThrows
    public Collection<String> provide() {
        try (final BufferedReader reader =
                     new BufferedReader(new InputStreamReader(dataResource.getInputStream(), StandardCharsets.UTF_8))) {

            final Collection<String> result = new ArrayList<>();
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                result.add(buffer);
            }
            return result;
        }
    }
}
