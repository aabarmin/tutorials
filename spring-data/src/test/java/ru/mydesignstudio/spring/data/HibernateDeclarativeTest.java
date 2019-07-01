package ru.mydesignstudio.spring.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.mydesignstudio.spring.data.model.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(locations = "classpath:hibernate-declarative-context.xml")
public class HibernateDeclarativeTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void check_sessionFactoryWorks() {
        assertNotNull(sessionFactory);
    }

    @Test
    void sessionFactory_executeQuery() {
        final List<Person> people = sessionFactory.openSession()
                .createQuery("FROM PERSON")
                .list();

        assertNotNull(people);
    }

    @Test
    void sessionFactory_createNewPerson() {
        final Person person = new Person("First Name", "Last Name");

        final Session session = sessionFactory.openSession();
        session.saveOrUpdate(person);

        assertTrue(person.getId() != 0);
    }
}
