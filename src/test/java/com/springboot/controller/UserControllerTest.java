package com.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.domain.UserEntity;
import com.springboot.controller.UserController;
import com.springboot.model.UserRequest;
import com.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
class UserControllerTest {

    /*
    TODO: mock tracing

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<UserEntity> userList;

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        this.userList.add(new UserEntity(1L, "user 1"));
        this.userList.add(new UserEntity(2L, "user 2"));
        this.userList.add(new UserEntity(3L, "user 3"));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void shouldFetchAllUsers() throws Exception {
        given(userService.findAllUsers()).willReturn(this.userList);

        this.mockMvc.perform(get("/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    void shouldFindUserById() throws Exception {
        Long userId = 1L;
        UserEntity user = new UserEntity(userId, "text 1");
        given(userService.findUserById(userId)).willReturn(Optional.of(user));

        this.mockMvc.perform(get("/v1/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(user.getText())))
        ;
    }

    @Test
    void shouldReturn404WhenFetchingNonExistingUser() throws Exception {
        Long userId = 1L;
        given(userService.findUserById(userId)).willReturn(Optional.empty());

        this.mockMvc.perform(get("v1/users/{id}", userId))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldCreateNewUser() throws Exception {
        UserRequest userRequest = new  UserRequest(5L, "new userRequest");
        given(userService.saveUser(userRequest)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateUser() throws Exception {
        Long userId = 1L;
        UserRequest userRequest = new UserRequest(userId, "user updated");
        given(userService.findUserById(userId)).willReturn(Optional.of(new UserEntity(userId, "user updated")));

        this.mockMvc.perform(put("/v1/users/{id}", userRequest.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteUser() throws Exception {
        Long userId = 1L;
        UserEntity user = new UserEntity(userId, "Some text");
        given(userService.findUserById(userId)).willReturn(Optional.of(user));
        doNothing().when(userService).deleteUserById(user.getId());

        this.mockMvc.perform(delete("/v1/users/{id}", user.getId()))
                .andExpect(status().isAccepted());
    }
     */
}
