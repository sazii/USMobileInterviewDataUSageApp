package com.usmobile.userservice.userservice.repository;

import com.usmobile.userservice.userservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUserIdAndActiveTrue(String userId);
    boolean existsUserByEmailAndActiveTrue(String email);
}
