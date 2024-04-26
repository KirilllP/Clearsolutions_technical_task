package com.clearsolutionstask.clearsolutionstask.mapper;

import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import com.clearsolutionstask.clearsolutionstask.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserDto userDto);

    User patchToUser(PatchUserDto patchUserDto);

}
