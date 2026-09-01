package com.user_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user_api.entity.User;
import com.user_api.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void shouldHideSensitiveFieldsInResponses() throws Exception {
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Ana");
        savedUser.setEmail("ana@email.com");
        savedUser.setPassword("senhaSecreta");
        savedUser.setCpf("12345678901");
        savedUser.setCreatedAt(LocalDateTime.of(2024, 1, 15, 10, 30));
        savedUser.setActive(true);

        when(userService.create(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ana",
                                  "email": "ana@email.com",
                                  "password": "senhaSecreta",
                                  "cpf": "12345678901"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana"))
                .andExpect(jsonPath("$.email").value("ana@email.com"))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.cpf").doesNotExist());

        verify(userService).create(argThat(user ->
                "senhaSecreta".equals(user.getPassword()) && "12345678901".equals(user.getCpf())));
    }

    @Test
    void shouldIgnoreCreatedAtAndActiveFromClient() throws Exception {
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Ana");
        savedUser.setEmail("ana@email.com");
        savedUser.setPassword("senhaSecreta");
        savedUser.setCpf("12345678901");
        savedUser.setCreatedAt(LocalDateTime.now());
        savedUser.setActive(true);

        when(userService.create(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ana",
                                  "email": "ana@email.com",
                                  "password": "senhaSecreta",
                                  "cpf": "12345678901",
                                  "createdAt": "2099-01-01T00:00:00",
                                  "active": false
                                }
                                """))
                .andExpect(status().isOk());

        verify(userService).create(argThat(user ->
                user.getCreatedAt() == null && user.getActive() == null));
    }
}
