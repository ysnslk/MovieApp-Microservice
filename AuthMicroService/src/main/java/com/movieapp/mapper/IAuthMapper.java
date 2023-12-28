package com.movieapp.mapper;

import com.movieapp.dto.request.CreateAuthRequestDto;
import com.movieapp.dto.request.CreateUserRequestDto;
import com.movieapp.dto.request.UpdateAuthRequestDto;
import com.movieapp.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth saveToAuth(final CreateAuthRequestDto createAuthRequestDto);

    Auth updateToAuth(final UpdateAuthRequestDto updateAuthRequestDto);
    CreateUserRequestDto convertToCreateUser(final CreateAuthRequestDto createAuthRequestDto);
}
