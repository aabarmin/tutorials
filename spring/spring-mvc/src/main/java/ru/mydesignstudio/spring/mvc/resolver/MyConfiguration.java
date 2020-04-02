package ru.mydesignstudio.spring.mvc.resolver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Locale;

@Configuration
@ComponentScan("ru.mydesignstudio.spring.mvc.resolver")
public class MyConfiguration {
    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("language");
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        final LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
        changeInterceptor.setParamName("changeLanguage");
        return changeInterceptor;
    }

    @Bean
    public ThemeSource themeSource() {
        final ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
        return themeSource;
    }

    @Bean
    public ViewResolver viewResolver(ISpringTemplateEngine templateEngine,
                                     ApplicationContext applicationContext) {
        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setApplicationContext(applicationContext);
        return viewResolver;
    }

    @Bean
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("/templates/");
        templateResolver.setCheckExistence(true);
        return templateResolver;
    }

    @Bean
    public Converter<String, MyComplexValue> complexValueConverter() {
        return new Converter<String, MyComplexValue>() {
            @Override
            public MyComplexValue convert(String source) {
                return new MyComplexValue(source);
            }
        };
    }
}
