package com.springboot.controller;

import com.springboot.constant.Constant;
import com.springboot.domain.UserEntity;
import com.springboot.exception.ExceptionResponse;
import com.springboot.message.MessageProducer;
import com.springboot.model.UserRequest;
import com.springboot.model.UserResponse;
import com.springboot.service.UserService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@Slf4j
@Api(value = "UserController", produces = "application/json", tags = { "Controlador User" })
public class UserController {

    private final UserService userService;
    private Tracer tracer;
    private MessageProducer messageProducer;

    @Autowired
    public UserController(UserService userService, Tracer tracer, MessageProducer messageProducer) {
        this.userService = userService;
        this.tracer = tracer;
        this.messageProducer = messageProducer;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        Span span = tracer.buildSpan("GET ALL User").start();
        List<UserEntity> listUser = userService.findAllUsers();
        messageProducer.sendMessage("GET ALL User - MESSAGE TEST");
        span.finish();
        return listUser;
    }

    @ApiOperation(value = "Obtiene User por ID", tags = { "Controlador User" })
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User encontrada", response = UserEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        Span span = tracer.buildSpan("GET User/" + id).start();

        messageProducer.sendMessage("GET User" + id + " - MESSAGE TEST");

        ResponseEntity<UserEntity> responseEntity = userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        span.finish();
        return responseEntity;
    }

    @ApiOperation(value = "Registra User", tags = { "Controlador User" })
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User registrada", response = UserRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserResponse> createUser(@RequestBody @Validated UserRequest userRequest) {
        Span span = tracer.buildSpan("POST User").start();

        messageProducer.sendMessage("POST User - MESSAGE TEST");

        userService.saveUser(userRequest);
        span.finish();
        return new ResponseEntity<>(new UserResponse(Constant.REG_INS_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza User", tags = { "Controlador User" })
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User actualizada", response = UserRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) throws Exception {
        Span span = tracer.buildSpan("UPDATE User/" + id).start();
        messageProducer.sendMessage("UPDATE User" + id + " - MESSAGE TEST");
        userService.updateUser(userRequest, id);
        span.finish();
        return new ResponseEntity<>(new UserResponse(Constant.REG_ACT_ACCEPTED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina User", tags = { "Controlador User" })
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User eliminada", response = UserRequest.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ExceptionResponse.class)})
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        Span span = tracer.buildSpan("DELETE User/" + id).start();
        messageProducer.sendMessage("DELETE User" + id + " - MESSAGE TEST");
        userService.deleteUserById(id);
        span.finish();
        return new ResponseEntity<>(new UserResponse(Constant.REG_ELI_OK), HttpStatus.ACCEPTED);
    }
}
