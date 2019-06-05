package ru.mydesignstudio.spring.aop.aspect.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({
    "ru.mydesignstudio.spring.aop.aspect.annotation",
    "ru.mydesignstudio.spring.aop.service"
})
@EnableAspectJAutoProxy
public class AnnotationAspectConfiguration {
}
