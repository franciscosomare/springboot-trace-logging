package com.springboot.service;

import com.springboot.domain.UserEntity;
import com.springboot.model.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> findAllUsers();

    public Optional<UserEntity> findUserById(Long id);

    public UserEntity saveUser(UserRequest userRequest);

    public UserEntity updateUser(UserRequest userRequest, Long id) throws Exception;

    public void deleteUserById(Long id);
}
