package ru.spring.mvc.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.mvc.model.Person;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Component
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> index() {
        TypedQuery<Person> getPeople = entityManager.createQuery(
                "SELECT us FROM Person us", Person.class);
        return getPeople.getResultList();
    }

    public Person show(int id) {
        TypedQuery<Person> getPeople = entityManager.createQuery(
                "SELECT us FROM Person us WHERE us.id =: personid", Person.class);
        getPeople.setParameter("personid", id);
        return getPeople.getSingleResult();
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        entityManager.merge(personToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        Person personToBeDeleted = show(id);
        entityManager.remove(personToBeDeleted);
    }
}
