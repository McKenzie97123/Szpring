package com.example.szpring.controller;

import com.example.szpring.entity.User;
import com.example.szpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> editUserById(
            @PathVariable Integer id,
            @RequestBody User editUser
    ) {
        User user = userService.editUser(id, editUser);

        return ResponseEntity.accepted().body(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeUser(@PathVariable Integer id) {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addUser(@RequestBody User newUser) {
        newUser.setCreateDate(new Timestamp(System.nanoTime()));
        userService.insertUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
