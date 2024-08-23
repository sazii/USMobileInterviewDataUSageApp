package com.usmobile.userservice.userservice.data.response;

import com.usmobile.userservice.userservice.data.UserDto;
import lombok.Builder;
import lombok.Data;

/******
 * {id, firstName, lastName, email}
 */
@Data
@Builder
public class UpdateUserResponseDTO {
    UserDto userDto;
    String message;
}
