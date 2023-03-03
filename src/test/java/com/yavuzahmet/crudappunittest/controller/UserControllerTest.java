package com.yavuzahmet.crudappunittest.controller;

import com.yavuzahmet.crudappunittest.dto.UserDto;
import com.yavuzahmet.crudappunittest.dto.UserRequest;
import com.yavuzahmet.crudappunittest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserControllerTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    @DisplayName("When saveUser called with userRequest it should user create and return userDto")
    void whenSaveUserCalledWithUserRequest_itShouldUserCreateAndReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String testUsername = "ahmetyavuz";
        UserRequest userRequest = new UserRequest(testUsername);
        UserDto userDto = new UserDto(testId, testUsername);

        Mockito.when(userService.saveUser(userRequest)).thenReturn(userDto);

        UserDto result = userController.saveUser(userRequest);

        assertEquals(result, userDto);
        Mockito.verify(userService).saveUser(userRequest);
    }

    @Test
    @DisplayName("When getUser called with valid userId it should return UserDto")
    void whenGetUserCalledWithValidUserId_itShouldReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String testUsername = "ahmetyavuz";
        UserDto userDto = new UserDto(testId, testUsername);

        Mockito.when(userService.getUser(testId)).thenReturn(userDto);

        UserDto result = userController.getUser(testId);

        assertEquals(result, userDto);

        Mockito.verify(userService).getUser(testId);
    }

    @Test
    @DisplayName("When getUser called with invalid userId it should not return userDto")
    void whenGetUserCalledWithInvalidUserId_itShouldNotReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";

        Mockito.when(userService.getUser(testId)).thenReturn(null);

        UserDto userDto = userController.getUser(testId);

        assertNull(userDto);
    }

    @Test
    @DisplayName("When getAllUser called it should return all UserDto")
    void whenGetAllUserCalled_itShouldReturnAllUserDto() {
        List<UserDto> userDtoList = Arrays.asList(
                new UserDto("1", "ahmet"),
                new UserDto("2", "yavuz"),
                new UserDto("3", "yavuzzz")
        );

        Mockito.when(userService.getAllUser()).thenReturn(userDtoList);

        List<UserDto> result = userController.getAllUser();

        assertEquals(result, userDtoList);

        Mockito.verify(userService).getAllUser();
    }

    @Test
    @DisplayName("When updateUser called with userRequest and valid userId it should user update and return userDto")
    void whenUpdateUserCalledWithUserRequestAndValidUserId_itShouldUserUpdateAndReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String oldTestUsername = "ahmet yavuz";
        String newTestUsername = "yavuz ahmet";
        UserRequest userRequest = new UserRequest(oldTestUsername);
        UserDto userDto = new UserDto(testId, newTestUsername);

        Mockito.when(userService.updateUser(testId, userRequest)).thenReturn(userDto);

        UserDto result = userController.updateUser(testId, userRequest);

        assertEquals(result, userDto);

        Mockito.verify(userService).updateUser(testId, userRequest);
    }

    @Test
    @DisplayName("When updateUser called with invalid userId it should not return userDto")
    void whenUpdateUserCalledWithInvalidUserId_itShouldNotReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";

        Mockito.when(userService.updateUser(testId, new UserRequest())).thenReturn(null);

        UserDto userDto = userController.updateUser(testId, new UserRequest());

        assertNull(userDto);
    }

    @Test
    @DisplayName("When deleteUser called with valid UserId it should userDelete")
    void whenDeleteUserCalledWithValidUserId_itShouldUserDelete() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String message = "User deleted with this id :" + testId;

        Mockito.when(userService.deleteUserById(testId)).thenReturn(message);

        String result = userController.deleteUserById(testId);

        assertEquals(result, message);

        Mockito.verify(userService).deleteUserById(testId);
    }

    @Test
    @DisplayName("When deleteUser called with invalid userId it should not return userDto")
    void whenDeleteUserCalledWithInvalidUserId_itShouldNotReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";

        Mockito.when(userService.deleteUserById(testId)).thenReturn(null);

        String message = userController.deleteUserById(testId);

        assertNull(message);
    }

}