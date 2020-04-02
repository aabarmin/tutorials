package ru.mydesignstudio.kotlin.samples.person

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PersonServiceImplTest {
    private val unitUnderTest = PersonServiceImpl(PersonDaoImpl())

    @BeforeEach
    internal fun setUp() {
        for (person in unitUnderTest.findAll()) {
            unitUnderTest.delete(person)
        }
    }

    @Test
    internal fun find_throwsException() {
        assertThrows(IllegalArgumentException::class.java) {
            unitUnderTest.findPerson(0)
        }
    }

    @Test
    internal fun find_shouldFindSomething() {
        var savedPerson = unitUnderTest.savePerson(Person(
                "Ivanov",
                "Ivan"
        ))

        assertTrue {
            savedPerson.id != 0
        }

        var foundPerson = unitUnderTest.findPerson(savedPerson.id)

        assertNotNull(foundPerson)
    }

    @Test
    internal fun findAll_shouldReturnAddedItems() {
        unitUnderTest.savePerson(Person(
                "Ivanov",
                "Ivan"
        ))
        unitUnderTest.savePerson(Person(
                "Petrov",
                "Petr"
        ))

        var persons = unitUnderTest.findAll()

        assertNotNull(persons)
    }
}
