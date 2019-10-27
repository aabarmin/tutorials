package ru.mydesignstudio.kotlin.samples.person

interface PersonService {
    fun findAll(): Collection<Person>

    fun findPerson(id: Int): Person

    fun savePerson(person: Person): Person

    fun delete(person: Person)
}
