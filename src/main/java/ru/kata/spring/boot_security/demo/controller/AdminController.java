package ru.kata.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import ru.kata.spring.boot_security.demo.model.Role;

import ru.kata.spring.boot_security.demo.service.RoleService;

import ru.kata.spring.boot_security.demo.service.UserServiceImpl;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final RoleService roleService;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleService roleService, PasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "admin";
    }
    @GetMapping("/addUser")
    public String addUser(Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userServiceImpl.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/users/remove/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userServiceImpl.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/users/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", userServiceImpl.findUserById(id));
        return "edit";
    }
    @PatchMapping("/users/{id}")
    public String editUser (@ModelAttribute("user") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userServiceImpl.updateUser(user);

        return "redirect:/admin";
    }
}
