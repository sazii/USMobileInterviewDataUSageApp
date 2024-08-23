package com.usmobile.services.mdnservice.domain;

import com.usmobile.shared.model.data.BaseData;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class MDNUserInfo extends BaseData {
    @Id
    String id;
    String mdn;
    String userId;
    Integer cycleDayOfMonth;
}
