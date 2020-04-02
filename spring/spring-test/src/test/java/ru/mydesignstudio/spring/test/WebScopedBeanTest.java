package ru.mydesignstudio.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitWebConfig
public class WebScopedBeanTest {
    @Autowired
    private AmazingService amazingService;
    @Autowired
    private MockHttpServletRequest servletRequest;

    @Test
    void check_contextStarts() {
        servletRequest.setParameter("value", "value");

        assertAll(
                () -> assertNotNull(amazingService),
                () -> assertNotNull(amazingService.scopedBean),
                () -> assertEquals("value", amazingService.scopedBean.getValue())
        );
    }

    @Configuration
    @Import(ScopedBean.class)
    static class Config {
        @Bean
        AmazingService amazingService(ScopedBean scopedBean) {
            return new AmazingService(scopedBean);
        }
    }

    @Service
    static class AmazingService {
        @Autowired
        private ScopedBean scopedBean;

        public AmazingService(ScopedBean scopedBean) {
            this.scopedBean = scopedBean;
        }
    }

    @Component
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    static class ScopedBean {
        @Value("#{request.getParameter('value')}")
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
