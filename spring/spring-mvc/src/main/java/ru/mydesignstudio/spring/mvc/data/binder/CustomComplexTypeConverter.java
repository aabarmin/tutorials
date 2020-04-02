package ru.mydesignstudio.spring.mvc.data.binder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

@Component
public class CustomComplexTypeConverter implements Converter<String, CustomComplexType> {
    private DateTimeFormatter dateFormat;

    @PostConstruct
    public void init() {
        dateFormat = DateTimeFormatter.ofPattern("M-dd-uuuu");
    }

    @Override
    public CustomComplexType convert(String source) {
        final TemporalAccessor accessor = dateFormat.parse(source);
        return new CustomComplexType(
                accessor.get(ChronoField.YEAR),
                accessor.get(ChronoField.MONTH_OF_YEAR),
                accessor.get(ChronoField.DAY_OF_MONTH)
        );
    }
}
