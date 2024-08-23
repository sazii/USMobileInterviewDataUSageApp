package com.usmobile.services.mdnservice.mapper;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import com.usmobile.services.mdnservice.domain.MDNUserInfo;
import com.usmobile.shared.model.converter.BaseDataConverter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MDNUserInfoMapper extends BaseDataConverter<MDNUserInfo, MDNUserInfoDTO> {
}
