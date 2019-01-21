package ru.mydesignstudio.mentoring.find.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.mydesignstudio.mentoring.find.spring.service.app.AnalyzerApp;
import ru.mydesignstudio.mentoring.find.spring.service.command.CommandProvider;

@Configuration
@ComponentScan(basePackageClasses = {
        AnalyzerApp.class,
        CommandProvider.class
})
@Import(CommandProviderConfiguration.class)
public class GeneralConfiguration {
}
