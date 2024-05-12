package com.example.unitech.mapper;

import com.example.unitech.dto.request.create.UserCreateRequest;
import com.example.unitech.dto.response.create.UserCreateResponse;
import com.example.unitech.dto.response.login.UserLoginResponse;
import com.example.unitech.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User mapToUser(UserCreateRequest userRequest);
    UserCreateResponse mapToUserCreateResponse(User user);

    UserLoginResponse entityToLoginResponse(User user);
}
