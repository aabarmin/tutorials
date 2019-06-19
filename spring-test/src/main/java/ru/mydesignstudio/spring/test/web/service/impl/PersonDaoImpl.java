package ru.mydesignstudio.spring.test.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.mydesignstudio.spring.test.web.model.Person;

import java.sql.ResultSet;
import java.util.Collection;

@Repository
public class PersonDaoImpl implements PersonDao {
    private static ResultSetExtractor<Person> PERSON_EXTRACTOR = (ResultSet rs) ->
            new Person(
                    rs.getInt("ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME")
            );

    private static RowMapper<Person> PERSON_MAPPER = (ResultSet rs, int rowNum) ->
            PERSON_EXTRACTOR.extractData(rs);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Person findOne(int id) {
        return jdbcTemplate.query("SELECT * FROM PEOPLE WHERE ID = ?", new Object[]{
                id
        }, PERSON_EXTRACTOR);
    }

    @Override
    public Person save(Person person) {
        person.setId(generateId());
        jdbcTemplate.update("INSERT INTO PEOPLE (ID, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?)", new Object[]{
                person.getId(),
                person.getFirstName(),
                person.getLastName()
        });
        return person;
    }

    private int generateId() {
        return findAll().stream()
                .mapToInt(Person::getId)
                .summaryStatistics()
                .getMax() + 1;
    }

    @Override
    public Collection<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM PEOPLE", PERSON_MAPPER);
    }
}
