package com.usmobile.shared.model.converter;

import java.util.List;
import java.util.Set;

public interface BaseDataConverter<Entity, Dto> {
    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
    List<Entity> toEntity(List<Dto> dtoList);
    List<Dto> toDto(List<Entity> entityList);
    Set<Entity> toEntity(Set<Dto> dtoSet);
    Set<Dto> toDto(Set<Entity> entitySet);
}
