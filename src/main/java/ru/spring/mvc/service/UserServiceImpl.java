package ru.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spring.mvc.dao.UserDAO;
import ru.spring.mvc.model.User;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUser() {
       return userDAO.index();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.show(id);
    }

    @Override
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Override
    public void updateUser(int id, User user) {
        userDAO.update(id, user);
    }
}
