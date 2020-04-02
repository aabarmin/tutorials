package ru.mydesignstudio.kotlin.samples.person

interface PersonDao {
    fun findPerson(id: Int): Person

    fun savePerson(person: Person): Person

    fun delete(person: Person)

    fun findAll(): Collection<Person>
}
