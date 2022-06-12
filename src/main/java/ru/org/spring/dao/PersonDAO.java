package ru.org.spring.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.org.spring.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Person> index() {

        return entityManager.createQuery("from Person", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
        entityManager.flush();
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        entityManager.find(Person.class, id);
        entityManager.merge(updatedPerson);
        entityManager.flush();
    }

    public Person readPerson(int id) {
        return entityManager.find(Person.class, id);
    }
    @Transactional
    public void delete(int id) {
        Person user = readPerson(id);
        entityManager.remove(user);
        entityManager.flush();

    }
}
