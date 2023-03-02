package com.yavuzahmet.crudappunittest.converter;

import com.yavuzahmet.crudappunittest.dto.UserDto;
import com.yavuzahmet.crudappunittest.dto.UserRequest;
import com.yavuzahmet.crudappunittest.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toEntity(UserRequest from) {
        var user = new User();
        user.setUsername(from.getUsername());
        return user;
    }

    public UserDto toDto(User from) {
        var dto = new UserDto();
        dto.setId(from.getId());
        dto.setUsername(from.getUsername());
        return dto;
    }
}
