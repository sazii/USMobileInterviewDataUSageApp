package com.usmobile.userservice.userservice.data.response;

import com.usmobile.userservice.userservice.data.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponseDTO {
    UserDto userDto;
    String message;
}
