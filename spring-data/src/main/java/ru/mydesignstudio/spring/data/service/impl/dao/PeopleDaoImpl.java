package ru.mydesignstudio.spring.data.service.impl.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.spring.data.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

@Component
public class PeopleDaoImpl implements PeopleDao {
    private static ResultSetExtractor<Person> PERSON_EXTRACTOR = (ResultSet rs) ->
            new Person(
                    rs.getInt("ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME")
            );

    private static RowMapper<Person> PERSON_MAPPER = (ResultSet rs, int rowNum) ->
            PERSON_EXTRACTOR.extractData(rs);

    private final JdbcTemplate jdbcTemplate;

    public PeopleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM PEOPLE", PERSON_MAPPER);
    }

    @Override
    public Person findOne(int id) {
        return jdbcTemplate.query("SELECT * FROM PEOPLE WHERE ID = ?", new Object[]{
                id
        }, PERSON_EXTRACTOR);
    }

    @Override
    public Person save(Person person) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            final PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO PEOPLE (FIRST_NAME, LAST_NAME) VALUES (?, ?)");
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            return statement;
        }, keyHolder);
        person.setId(keyHolder.getKey().intValue());
        return person;
    }
}
