package com.usmobile.userservice.userservice.mapper;

import com.usmobile.shared.model.converter.BaseDataConverter;
import com.usmobile.userservice.userservice.data.UserDto;
import com.usmobile.userservice.userservice.domain.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper extends BaseDataConverter<User, UserDto> {
}
