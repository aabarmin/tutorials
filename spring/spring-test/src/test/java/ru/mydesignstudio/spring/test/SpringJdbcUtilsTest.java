package ru.mydesignstudio.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        InMemoryDataSourceConfiguration.class
})
@Sql("classpath:create-tables.sql")
public class SpringJdbcUtilsTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void jdbcTestUtils() {
        final int rowsInTable = JdbcTestUtils.countRowsInTable(jdbcTemplate, "test");

        assertEquals(0, rowsInTable);
    }
}
