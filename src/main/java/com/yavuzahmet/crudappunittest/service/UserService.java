package com.yavuzahmet.crudappunittest.service;

import com.yavuzahmet.crudappunittest.converter.UserConverter;
import com.yavuzahmet.crudappunittest.dto.UserDto;
import com.yavuzahmet.crudappunittest.dto.UserRequest;
import com.yavuzahmet.crudappunittest.exception.NotFoundException;
import com.yavuzahmet.crudappunittest.model.User;
import com.yavuzahmet.crudappunittest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDto saveUser(UserRequest userRequest) {
        var user = userConverter.toEntity(userRequest);
        return userConverter.toDto(userRepository.save(user));
    }

    public UserDto getUser(String id) {
        return userConverter.toDto(findUserById(id));
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(userConverter::toDto).collect(Collectors.toList());
    }

    public UserDto updateUser(String id, UserRequest userRequest) {
        var user = findUserById(id);
        user.setUsername(userRequest.getUsername());
        return userConverter.toDto(userRepository.save(user));
    }

    public String deleteUserById(String id) {
        findUserById(id);
        userRepository.deleteById(id);
        return "User deleted with this id :" + id;
    }

    private User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }
}
