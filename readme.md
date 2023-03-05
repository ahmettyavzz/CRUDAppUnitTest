## <span style="color:gray"> Unit Test for User CRUD Application </span>

_Unit Test scenarios have been written for the User CRUD application._


### <span style="color:darkgoldenrod"> UserControllerTest - Unit tests of the UserController class </span>

- **whenSaveUserCalledWithUserRequest_itShouldUserCreateAndReturnUserDto** - Unit test for user create
- **whenGetUserCalledWithValidUserId_itShouldReturnUserDto** - User test for get user with valid id
- **whenGetUserCalledWithInvalidUserId_itShouldNotReturnUserDto** - User test for get user with invalid id
- **whenGetAllUserCalled_itShouldReturnAllUserDto** - User test for get all user with
- **whenUpdateUserCalledWithUserRequestAndValidUserId_itShouldUserUpdateAndReturnUserDto** - User test for update user with valid id
- **whenUpdateUserCalledWithInvalidUserId_itShouldNotReturnUserDto** - User test for update user with invalid id
- **whenDeleteUserCalledWithValidUserId_itShouldUserDelete** - User test for delete user with valid id
- **whenDeleteUserCalledWithInvalidUserId_itShouldNotReturnUserDto** - User test for delete user with invalid id

<hr/>

### <span style="color:darkgoldenrod"> UserServiceTest - Unit tests of the UserService class </span>



- **whenCreateUserCalledWithValidUserRequest_itShouldReturnValidUserDto** - Unit test for user create
- **whenGetUserCalledWithValidUserId_itShouldReturnUserDto** - User test for get user with valid id
- **whenGetUserCalledWithInvalidUserId_itShouldNotReturnUserDto** - User test for get user with invalid id
- **whenGetAllUserCalled_itShouldReturnAllUserDto** - User test for get all user with
- **whenUpdateUserCalledWithValidUserIdAndUserRequest_itShouldReturnUpdatedUserDto** - User test for update user with valid id
- **whenUpdateUserCalledWithInvalidUserIdAndUserRequest_itShouldNotReturnUpdatedUserDto** - User test for update user with invalid id
- **whenDeleteUserCalledWithValidUserId_itShouldDeleteUser** - User test for delete user with valid id
- **whenDeleteUserCalledWithInvalidUserId_itShouldThrowException** - User test for delete user with invalid id

<hr/>

### <span style="color:darkgoldenrod"> UserConverterTest - Unit tests of the UserConverter class </span>

- **whenToEntityCalledWithUserRequest_itShouldReturnUser** - Unit test for converting UserRequest to User
- **whenToDtoCalledWithUser_itShouldReturnUserDto** - Unit test for converting User to UserDto