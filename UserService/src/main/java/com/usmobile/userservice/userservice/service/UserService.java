package com.usmobile.userservice.userservice.service;

import com.usmobile.userservice.userservice.data.request.CreateUserRequestDto;
import com.usmobile.userservice.userservice.data.request.UpdateUserRequestDTO;
import com.usmobile.userservice.userservice.data.response.CreateUserResponseDTO;
import com.usmobile.userservice.userservice.data.response.UpdateUserResponseDTO;


public interface UserService {

    CreateUserResponseDTO createUser(CreateUserRequestDto requestDTO);

    UpdateUserResponseDTO updateUserProfile(UpdateUserRequestDTO requestDTO) throws Exception;
}
