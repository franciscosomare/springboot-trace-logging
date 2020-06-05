package com.springboot.service.impl;

import com.springboot.domain.UserEntity;
import com.springboot.repository.UserRepository;
import com.springboot.model.UserRequest;
import com.springboot.service.impl.mapper.UserDTOToUserEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.service.UserService;
import com.springboot.constant.Constant;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserDTOToUserEntityMapper userDTOToUserEntityMapper = new UserDTOToUserEntityMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity saveUser(UserRequest userRequest) {
        UserEntity user = userDTOToUserEntityMapper.userDTOToUserEntityMapper(userRequest);
        return userRepository.save(user);
    }

    public UserEntity updateUser(UserRequest userRequest, Long id) throws Exception {
        return userRepository.findById(id).map(userRequestObje -> {
            userRequest.setId(id);
            UserEntity user = userDTOToUserEntityMapper.userDTOToUserEntityMapper(userRequest);
            return userRepository.save(user);
        })
        .orElseThrow(() -> new Exception(Constant.PERSONA_NOT_FOUND));
    }

    public void deleteUserById(Long id) {
        userRepository.findById(id).ifPresent(delete -> userRepository.deleteById(id));
    }
}