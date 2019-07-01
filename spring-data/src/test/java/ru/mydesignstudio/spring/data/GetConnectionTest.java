package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(SpringDataConfiguration.class)
public class GetConnectionTest {
    @Autowired
    private DataSource dataSource;

    @Test
    void getConnection() {
        final Connection connection = DataSourceUtils.getConnection(dataSource);

        assertNotNull(connection);
    }
}
