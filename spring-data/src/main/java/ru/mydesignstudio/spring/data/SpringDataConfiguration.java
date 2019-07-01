package ru.mydesignstudio.spring.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = "classpath:data-source-config.xml")
public class SpringDataConfiguration {

}
