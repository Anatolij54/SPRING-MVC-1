package ru.spring.mvc.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.mvc.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Component
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> index() {
        TypedQuery<User> getUsers = entityManager.createQuery(
                "SELECT us FROM User us", User.class);
        return getUsers.getResultList();
    }

    public User show(int id) {
        TypedQuery<User> getUser = entityManager.createQuery(
                "SELECT us FROM User us WHERE us.id =: userid", User.class);
        getUser.setParameter("userid", id);
        return getUser.getSingleResult();
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        entityManager.merge(userToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        User userToBeDeleted = show(id);
        entityManager.remove(userToBeDeleted);
    }
}
