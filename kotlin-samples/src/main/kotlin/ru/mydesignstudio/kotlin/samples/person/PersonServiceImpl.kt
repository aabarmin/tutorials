package ru.mydesignstudio.kotlin.samples.person

class PersonServiceImpl(private val personDao: PersonDao): PersonService {
    override fun findAll(): Collection<Person> = personDao.findAll()

    override fun findPerson(id: Int): Person = personDao.findPerson(id)

    override fun savePerson(person: Person): Person = personDao.savePerson(person)

    override fun delete(person: Person) = personDao.delete(person)
}
