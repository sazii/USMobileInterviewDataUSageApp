package com.usmobile.userservice.userservice.domain;

import com.usmobile.shared.model.data.BaseData;
import com.usmobile.userservice.userservice.constants.UserServiceConstants;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

/******
 * String: id, primary key, auto generated by MongoDB
 *
 * String: firstName, first name of the customer
 *
 * String: lastName, last name of the customer
 *
 * String: email, email of the customer
 *
 * String: password, used for the login to our system
 */
@Data
@Builder
public class User extends BaseData {
    @Id
    String id;
    String userId;
    String firstName;
    String lastName;
    String email;
    String password;
    LocalDateTime startDate;
    LocalDateTime endDate;
    boolean active;

    public String generateId() { return UserServiceConstants.IdPrefixes.USER_ID_PREFIX + UUID.randomUUID();}

    public String getFullName(){ return getFirstName() + " " + getLastName();}

}
