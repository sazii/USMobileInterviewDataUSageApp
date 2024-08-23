package com.usmobile.services.mdnservice.repository;

import com.usmobile.services.mdnservice.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    UserInfo findUserInfoByUserIdAndActiveTrue(String userId);

}
