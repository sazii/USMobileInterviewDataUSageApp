package com.usmobile.services.mdnservice.repository;

import com.usmobile.services.mdnservice.domain.MDNUserInfo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MDNUserRepository extends MongoRepository<MDNUserInfo, String> {

    boolean existsMDNUserInfoByMdn(String mdn);
    MDNUserInfo findMDNUserInfoByMdnAndUserId(String mdn, String userId);
}
