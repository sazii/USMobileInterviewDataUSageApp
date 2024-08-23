package com.usmobile.userservice.userservice.service.impl;

import com.usmobile.shared.model.data.UserEventDto;
import com.usmobile.userservice.userservice.data.UserRequestDto;
import com.usmobile.userservice.userservice.data.request.CreateUserRequestDto;
import com.usmobile.userservice.userservice.data.request.UpdateUserRequestDTO;
import com.usmobile.userservice.userservice.data.response.CreateUserResponseDTO;
import com.usmobile.userservice.userservice.data.response.UpdateUserResponseDTO;
import com.usmobile.userservice.userservice.domain.User;
import com.usmobile.userservice.userservice.exception.UserCouldNotBeFoundException;
import com.usmobile.userservice.userservice.kafka.KafkaSender;
import com.usmobile.userservice.userservice.mapper.UserMapper;
import com.usmobile.userservice.userservice.repository.UserRepository;
import com.usmobile.userservice.userservice.service.UserService;
import com.usmobile.userservice.userservice.validation.UserValidator;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidator validator;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final KafkaSender kafkaSender;
    public CreateUserResponseDTO createUser(CreateUserRequestDto requestDTO) {
        validator.runValidationsForCreate(requestDTO);
        User user = createUserFromRequest(requestDTO.getUserRequestDto());
        user.setUserId(user.generateId());
        user.setCreatedDate(LocalDateTime.now());
        user = repository.save(user);
        kafkaSender.send(UserEventDto.builder().active(true).userId(user.getUserId()).build());
        return CreateUserResponseDTO.builder()
                .userDto(mapper.toDto(user))
                .message("user is successfully created")
                .build();


    }

    private User createUserFromRequest(UserRequestDto userRequestDto){
        return User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .active(true)
                .password(userRequestDto.getPassword()) //crypto
                .email(userRequestDto.getEmail())
                .startDate(LocalDateTime.now())
                .build();
    }


    @Override
    public UpdateUserResponseDTO updateUserProfile(UpdateUserRequestDTO requestDTO) throws Exception{
        validator.runValidationsForUpdate(requestDTO);
        UserRequestDto userRequestDto = requestDTO.getUserRequestDto();
        User user = repository.findUserByUserIdAndActiveTrue(requestDTO.getUserId());
        if(ObjectUtils.isEmpty(user)){
            throw new UserCouldNotBeFoundException(requestDTO.getUserId());
        }
        updateUserFieldsIfWanted(userRequestDto, user);
        user.setUpdatedDate(LocalDateTime.now());
        return UpdateUserResponseDTO.builder()
                .userDto(mapper.toDto(repository.save(user)))
                .message("user is successfully updated")
                .build();
    }

    private void updateUserFieldsIfWanted(UserRequestDto userRequestDto, User user) {
        if(!StringUtils.isEmpty(userRequestDto.getLastName())) user.setLastName(userRequestDto.getLastName());
        if(!StringUtils.isEmpty(userRequestDto.getFirstName())) user.setFirstName(userRequestDto.getFirstName());
        if(!StringUtils.isEmpty(userRequestDto.getPassword())) user.setPassword(userRequestDto.getPassword());
        if(!StringUtils.isEmpty(userRequestDto.getEmail())) user.setEmail(userRequestDto.getEmail());
    }
}
