package com.usmobile.services.dataUsageService.data;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserIdMDNPair implements Comparable<UserIdMDNPair>{
    String userId;
    String mdn;
    @Override
    public int compareTo(UserIdMDNPair other) {
        return userId.equals(other.getUserId()) ? mdn.compareTo(other.getMdn()) : userId.compareTo(other.getUserId()) ;
    }
}
