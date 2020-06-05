package com.springboot.service.impl.mapper;

import org.modelmapper.ModelMapper;
import com.springboot.domain.UserEntity;
import com.springboot.model.UserRequest;

public class UserDTOToUserEntityMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public UserEntity userDTOToUserEntityMapper(UserRequest userRequest) {
        return modelMapper.map(userRequest, UserEntity.class);
    }
}