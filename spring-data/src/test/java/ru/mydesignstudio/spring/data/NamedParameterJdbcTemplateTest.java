package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.mydesignstudio.spring.data.model.Person;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(locations = "classpath:named-jdbc-template-context.xml")
@Sql("classpath:create-tables.sql")
public class NamedParameterJdbcTemplateTest {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Test
    void createEntriesAndRetrieveThem() {
        final String insertSql = "INSERT INTO PEOPLE (FIRST_NAME, LAST_NAME) VALUES (:firstName, :lastName)";

        final Person toCreate = new Person("First Name", "Last Name");

        jdbcTemplate.update(insertSql, new BeanPropertySqlParameterSource(toCreate));

        final String selectSql = "SELECT count(*) FROM PEOPLE";

        final int count = jdbcTemplate.queryForObject(selectSql, Collections.emptyMap(), Integer.class);

        assertTrue(count > 0);
    }
}
