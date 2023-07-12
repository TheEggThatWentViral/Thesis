package com.example.webshopbackend.service;

import com.example.webshopbackend.domain.Role;
import com.example.webshopbackend.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
