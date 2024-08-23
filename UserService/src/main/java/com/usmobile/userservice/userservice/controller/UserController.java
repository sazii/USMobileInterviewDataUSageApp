package com.usmobile.userservice.userservice.controller;

import com.usmobile.userservice.userservice.data.request.CreateUserRequestDto;
import com.usmobile.userservice.userservice.data.request.UpdateUserRequestDTO;
import com.usmobile.userservice.userservice.data.response.CreateUserResponseDTO;
import com.usmobile.userservice.userservice.data.response.UpdateUserResponseDTO;
import com.usmobile.userservice.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @PostMapping("/create")
    public CreateUserResponseDTO createUser(@RequestBody CreateUserRequestDto requestDTO) {
        return service.createUser(requestDTO);
    }

    @PutMapping("/updateProfile")
    public UpdateUserResponseDTO updateteUserProfile(@RequestBody UpdateUserRequestDTO requestDTO) throws Exception {
        return service.updateUserProfile(requestDTO);
    }

}
