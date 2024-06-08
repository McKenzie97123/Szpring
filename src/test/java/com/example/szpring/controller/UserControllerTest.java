package com.example.szpring.controller;


import com.example.szpring.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser
    public void returnAllUsersForRoleUser() throws Exception {
        mvc.perform(get("/user"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }

    @Test
    @WithMockUser(roles= {"ADMIN"})
    public void returnAllUsersForRoleUser() throws Exception {
        mvc.perform(get("/user"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }
}
