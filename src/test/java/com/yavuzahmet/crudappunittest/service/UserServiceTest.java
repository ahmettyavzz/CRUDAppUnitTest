package com.yavuzahmet.crudappunittest.service;

import com.yavuzahmet.crudappunittest.converter.UserConverter;
import com.yavuzahmet.crudappunittest.dto.UserDto;
import com.yavuzahmet.crudappunittest.dto.UserRequest;
import com.yavuzahmet.crudappunittest.model.User;
import com.yavuzahmet.crudappunittest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private UserConverter userConverter;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userConverter = Mockito.mock(UserConverter.class);

        userService = new UserService(userRepository, userConverter);
    }

    @Test
    @DisplayName("When saveUser called with valid userRequest it should return valid userDto")
        // 1. Method name
    void whenCreateUserCalledWithValidUserRequest_itShouldReturnValidUserDto() {
        // 2. Create parameters
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String testUsername = "ahmetyavuz";
        User userEntity = new User(testId, testUsername);
        UserRequest userRequest = new UserRequest(testUsername);
        UserDto userDto = new UserDto(testId, testUsername);
        // 3. Identifying uses of non-unit dependencies
        Mockito.when(userConverter.toEntity(userRequest)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userConverter.toDto(userEntity)).thenReturn(userDto);
        // 4. Run the test method
        UserDto result = userService.saveUser(userRequest);
        // 5. Compare results
        assertEquals(result, userDto);
        // 6. Check if methods are run
        Mockito.verify(userConverter).toEntity(userRequest);
        Mockito.verify(userRepository).save(userEntity);
        Mockito.verify(userConverter).toDto(userEntity);
    }

    @Test
    @DisplayName("When getUser called with valid userId it should return userDto")
    void whenGetUserCalledWithValidUserId_itShouldReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String testUsername = "ahmetyavuz";
        User user = new User(testId, testUsername);
        UserDto userDto = new UserDto(testId, testUsername);

        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(user));
        Mockito.when(userConverter.toDto(user)).thenReturn(userDto);

        UserDto result = userService.getUser(testId);

        assertEquals(result, userDto);

        Mockito.verify(userRepository).findById(testId);
        Mockito.verify(userConverter).toDto(user);
    }

    @Test
    @DisplayName("When getUser called with invalid userId it should not return userDto")
    void whenGetUserCalledWithInvalidUserId_itShouldNotReturnUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";

        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.empty());

        assertThrows(com.yavuzahmet.crudappunittest.exception.NotFoundException.class
                , () -> userService.getUser(testId));
    }

    @Test
    @DisplayName("When getAllUser it should return all UserDto")
    void whenGetAllUserCalled_itShouldReturnAllUserDto() {
        List<User> userEntityList = Arrays.asList(
                new User("1", "ahmet"),
                new User("2", "yavuz"),
                new User("3", "yavuzzz")
        );
        List<UserDto> userDtoList = Arrays.asList(
                new UserDto("1", "ahmet"),
                new UserDto("2", "yavuz"),
                new UserDto("3", "yavuzzz")
        );

        Mockito.when(userRepository.findAll()).thenReturn(userEntityList);
        Mockito.when(userConverter.toDto(any(User.class))).thenReturn(userDtoList.get(0), userDtoList.get(1), userDtoList.get(2));

        List<UserDto> result = userService.getAllUser();

        assertEquals(userDtoList, result);

        Mockito.verify(userRepository).findAll();
        Mockito.verify(userConverter, times(3)).toDto(any(User.class));
    }

    @Test
    @DisplayName("When updateUser called with valid userId and userRequest it should return updatedUserDto")
    void whenUpdateUserCalledWithValidUserIdAndUserRequest_itShouldReturnUpdatedUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";
        String newUsername = "ahmetyavuz";
        String oldUsername = "yavuzahmet";
        User user = new User(testId, oldUsername);
        UserRequest userRequest = new UserRequest(newUsername);
        UserDto userDto = new UserDto(testId, newUsername);

        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userConverter.toDto(user)).thenReturn(userDto);

        UserDto updatedUser = userService.updateUser(testId, userRequest);

        assertEquals(userRequest.getUsername(), user.getUsername());
        assertNotNull(updatedUser);

        Mockito.verify(userRepository, times(1)).findById(testId);
        Mockito.verify(userRepository, times(1)).save(user);
        Mockito.verify(userConverter, times(1)).toDto(user);
    }

    @Test
    @DisplayName("When updateUser called with invalid userId and userRequest it should not return updatedUserDto")
    void whenUpdateUserCalledWithInvalidUserIdAndUserRequest_itShouldNotReturnUpdatedUserDto() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";

        when(userRepository.findById(testId)).thenReturn(Optional.empty());

        assertThrows(com.yavuzahmet.crudappunittest.exception.NotFoundException.class,
                () -> userService.getUser(testId));
    }

    @Test
    @DisplayName("When deleteUser called with valid userId it should delete user")
    void whenDeleteUserCalledWithValidUserId_itShouldDeleteUser() {
        String testId = "9b97bc0c-0ab2-4772-a7a1-ced065e59478";

        Mockito.doNothing().when(userRepository).deleteById(testId);
        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(new User()));

        String result = userService.deleteUserById(testId);

        assertEquals("User deleted with this id :" + testId, result);

        Mockito.verify(userRepository, times(1)).deleteById(testId);
        Mockito.verify(userRepository, times(1)).findById(testId);
    }

}