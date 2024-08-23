package com.usmobile.userservice.userservice;

import com.usmobile.userservice.userservice.data.UserDto;
import com.usmobile.userservice.userservice.data.UserRequestDto;
import com.usmobile.userservice.userservice.data.request.CreateUserRequestDto;
import com.usmobile.userservice.userservice.data.request.UpdateUserRequestDTO;
import com.usmobile.userservice.userservice.domain.User;
import com.usmobile.userservice.userservice.mapper.UserMapper;
import com.usmobile.userservice.userservice.repository.UserRepository;
import com.usmobile.userservice.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceApplicationTests {

    @MockBean
    private  UserRepository repository;

    @Autowired
    private  UserService service;
    @Autowired
    private UserMapper mapper;

    @Test
    public void createUserTest(){
        String  name = "ali", lastname = "ada", email = "as.cz", password = "sdf";
        User user = createUser( name, lastname, email, password);
        when(repository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        UserDto expected = mapper.toDto(user);
        UserDto actual = service.createUser(creteRequestDto(name, lastname, email, password)).getUserDto();
        expected.setUserId(actual.getUserId());
        assertEquals(expected, actual);
    }
    @Test
    public void createUpdateUserProfileTest() throws Exception {
        String  userId = "US-25", name = "ali", lastname = "ada", email = "as.cz", password = "sdf";
        User user = createUser( name, lastname, email, password);
        user.setUserId(userId);
        when(repository.findUserByUserIdAndActiveTrue(userId)).thenReturn(user);
        when(repository.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        UserDto actual = service.updateUserProfile(createUpdateRequestDTO(user)).getUserDto();
        UserDto expected = mapper.toDto(user);
        assertEquals(expected, actual);
    }

    private UpdateUserRequestDTO createUpdateRequestDTO(User user) {
        UpdateUserRequestDTO requestDTO = new UpdateUserRequestDTO();
        requestDTO.setUserRequestDto(createUserRequestDto(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));
        requestDTO.setUserId(user.getUserId());
        return requestDTO;
    }

    private CreateUserRequestDto creteRequestDto(String name, String lastname, String email, String password) {
        CreateUserRequestDto requestDTO = new CreateUserRequestDto();
        requestDTO.setUserRequestDto(createUserRequestDto(name, lastname,email, password));
       return requestDTO;
    }

    private UserRequestDto createUserRequestDto(String name, String lastname, String email, String password) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstName(name);
        userRequestDto.setEmail(email);
        userRequestDto.setLastName(lastname);
        userRequestDto.setPassword(password);
        return userRequestDto;
    }

    private User createUser( String name, String lastname, String email, String password) {
        return User.builder()
                .firstName(name)
                .lastName(lastname)
                .active(true)
                .email(email)
                .password(password)
                .startDate(LocalDateTime.now()).build();
    }

}
