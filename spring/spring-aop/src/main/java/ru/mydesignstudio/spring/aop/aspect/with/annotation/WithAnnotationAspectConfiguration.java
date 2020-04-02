package ru.mydesignstudio.spring.aop.aspect.with.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({
    "ru.mydesignstudio.spring.aop.aspect.with.annotation",
    "ru.mydesignstudio.spring.aop.service"
})
public class WithAnnotationAspectConfiguration {
}
