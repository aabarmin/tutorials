package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringJUnitConfig(locations = "classpath:named-jdbc-template-context.xml")
@Sql("classpath:create-tables.sql")
public class SimpleJdbcInsertTest {
    @Autowired
    private DataSource dataSource;

    @Test
    void insert_usingSimpleJdbcInsert() {
        final SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PEOPLE");

        final Map<String, String> parameters = new HashMap<>();
        parameters.put("FIRST_NAME", "firstName");
        parameters.put("LAST_NAME", "lastName");

        jdbcInsert.execute(parameters);
    }
}
