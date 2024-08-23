package com.usmobile.services.mdnservice.domain;

import com.usmobile.shared.model.data.BaseData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserInfo extends BaseData {
    @Id
    String id;
    String userId;
    boolean active;
    Map<String, MDN> mdnMap;
}
