package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(locations = "classpath:declarative-embedded-datasource-context.xml")
public class DeclarativeEmbeddedDatasourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    void check_dataSourceCreated() {
        assertNotNull(dataSource);
    }

    @Test
    void check_table() throws Exception {
        try (final Connection connection = dataSource.getConnection()) {
            final ResultSet resultSet = connection.createStatement().executeQuery("select * from PEOPLE");
            assertNotNull(resultSet);
        }
    }
}
