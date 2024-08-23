package com.usmobile.userservice.userservice.data.request;

import com.usmobile.userservice.userservice.data.UserRequestDto;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    String userId;
    UserRequestDto userRequestDto;
}
