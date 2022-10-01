package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {
  void addUser(User user);
  void updateUser(User user, Long id);
  void removeUser(Long id);
  List<User> getAllUsers();


}
