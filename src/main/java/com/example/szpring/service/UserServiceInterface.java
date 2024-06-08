package com.example.szpring.service;

import com.example.szpring.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

public interface UserServiceInterface {
    void insertUser(User user);
    void deleteUser(long id);
    User editUser(long id, @RequestBody User newUser);
    User getUserById(long id);
    ArrayList<User> getUsers();
}
