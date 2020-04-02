package ru.mydesignstudio.kotlin.samples.person

class PersonDaoImpl: PersonDao {
    private val storage = hashMapOf<Int, Person>()

    override fun findPerson(id: Int): Person = storage[id] ?: throw IllegalArgumentException("Can't find person with id ${id}")

    override fun savePerson(person: Person): Person {
        if (person.id == 0) {
            person.id = storage.size + 1
        }
        storage[person.id] = person
        return person
    }

    override fun delete(person: Person) {
        storage.remove(person.id)
    }

    override fun findAll(): Collection<Person> = storage.values
}
