package com.example.szpring.service;

import com.example.szpring.entity.User;
import com.example.szpring.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class UserService implements UserServiceInterface{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User editUser(long id, @RequestBody User newUser) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setSurName(newUser.getSurName());
                    user.setHobby(newUser.getHobby());
                    user.setAge(newUser.getAge());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("Update user failed, user id: " + id));
    }

    @Override
    public User getUserById(long id) {

        return userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found" + id));
    }

    @Override
    public ArrayList<User> getUsers() {

        return (ArrayList<User>) userRepository.findAll();
    }
}
