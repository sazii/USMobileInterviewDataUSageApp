package com.usmobile.userservice.userservice.data;

import lombok.Data;

@Data
public class UserDto {
    String userId;
    String firstName;
    String lastName;
    String email;

    public String getFullName(){ return getFirstName() + " " + getLastName();}
}
