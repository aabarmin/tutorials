package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
public class JavaEmbeddedDatasourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    void check_contextStarts() {
        assertNotNull(dataSource);
    }

    @Configuration
    static class MyConfiguration {
        @Bean(destroyMethod = "shutdown")
        public EmbeddedDatabase dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .generateUniqueName(true)
                    .addScript("classpath:create-tables.sql")
                    .build();
        }
    }
}
