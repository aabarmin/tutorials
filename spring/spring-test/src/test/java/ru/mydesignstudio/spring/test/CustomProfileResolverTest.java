package ru.mydesignstudio.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ActiveProfilesResolver;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
@ActiveProfiles(resolver = CustomProfileResolverTest.MyProfileResolver.class)
public class CustomProfileResolverTest {
    @Autowired
    private SimpleBean simpleBean;

    @Test
    void check_contextStarts() {
        assertNotNull(simpleBean);
    }

    static class MyProfileResolver implements ActiveProfilesResolver {

        @Override
        public String[] resolve(Class<?> testClass) {
            return new String[]{
                    "first"
            };
        }
    }

    @Configuration
    static class Config {
        @Bean
        @Profile("first")
        SimpleBean firstProfile() {
            return new SimpleBean("first");
        }

        @Bean
        @Profile("second")
        SimpleBean secondProfile() {
            return new SimpleBean("second");
        }
    }

    static class SimpleBean {
        private final String profile;

        SimpleBean(String profile) {
            this.profile = profile;
        }
    }
}
