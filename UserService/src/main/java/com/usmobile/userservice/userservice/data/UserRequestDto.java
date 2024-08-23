package com.usmobile.userservice.userservice.data;

import lombok.Data;

@Data
public class UserRequestDto {
    String firstName;
    String lastName;
    String email;
    String password;

    public String getFullName(){ return getFirstName() + " " + getLastName();}
}
