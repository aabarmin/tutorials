package ru.mydesignstudio.spring.mvc.data.binder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Collection;

@Configuration
@ComponentScan("ru.mydesignstudio.spring.mvc.data.binder")
public class DataBinderConfiguration {
    @Autowired
    private Collection<Converter<?, ?>> converters;

    @Bean
    public ConversionService conversionService() {
        final DefaultConversionService conversionService = new DefaultConversionService();
        for (Converter<?, ?> converter : converters) {
            conversionService.addConverter(converter);
        }
        return conversionService;
    }
}
