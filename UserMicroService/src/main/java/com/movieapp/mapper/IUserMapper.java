package com.movieapp.mapper;

import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.request.UpdateUserRequestDto;
import com.movieapp.repository.entity.User;
import com.movieapp.repository.enums.ELanguage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

User saveToUser(final ELanguage language, CreateUserRequestDto dto);

}
