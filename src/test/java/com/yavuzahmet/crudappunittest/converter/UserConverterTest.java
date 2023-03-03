package com.yavuzahmet.crudappunittest.converter;

import com.yavuzahmet.crudappunittest.dto.UserDto;
import com.yavuzahmet.crudappunittest.dto.UserRequest;
import com.yavuzahmet.crudappunittest.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserConverterTest {
    private UserConverter userConverter;

    @BeforeEach
    void setUp() {
        userConverter = new UserConverter();
    }

    @Test
    @DisplayName("When toEntity called with userRequest it should return user")
    void whenToEntityCalledWithUserRequest_itShouldReturnUser() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String testUsername = "ahmetyavuz";
        User user = new User(testId, testUsername);
        UserRequest userRequest = new UserRequest(testUsername);

        User result = userConverter.toEntity(userRequest);

        assertNotNull(user);
        assertEquals(result.getUsername(), user.getUsername());
    }

    @Test
    @DisplayName("When toDto called with user it should return userDto")
    void whenToDtoCalledWithUser_itShouldReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String testUsername = "ahmetyavuz";
        User user = new User(testId, testUsername);
        UserDto userDto = new UserDto(testId, testUsername);

        UserDto result = userConverter.toDto(user);

        assertNotNull(userDto);
        assertEquals(result.getUsername(), userDto.getUsername());
        assertEquals(result.getId(), userDto.getId());
    }

}
