package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findUserById(long id);
    User findByUsername(String username);
    List < User > getAllUsers();
    void addUser(User user);
    void updateUser(User user);
    void removeUser(Long id);
}