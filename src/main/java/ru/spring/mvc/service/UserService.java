package ru.spring.mvc.service;

import ru.spring.mvc.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User getUserById(int id);
    public void saveUser(User user);
    public void deleteUser(int id);
    public void updateUser(int id, User user);
}
