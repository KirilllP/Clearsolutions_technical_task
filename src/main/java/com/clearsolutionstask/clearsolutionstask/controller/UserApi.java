package com.clearsolutionstask.clearsolutionstask.controller;

import com.clearsolutionstask.clearsolutionstask.dto.DateRangeDto;
import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface UserApi {

    @Operation(summary = "save new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "user saved"),
            @ApiResponse(responseCode = "400", description = "validation error")
    })
    UserDto saveUser(UserDto userDto);

    @Operation(summary = "delete user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "user deleted"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    void deleteUser(Integer id);

    @Operation(summary = "patch user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "user patched"),
            @ApiResponse(responseCode = "400", description = "validation error"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    UserDto patchUser(Integer id, PatchUserDto patchUserDto) throws IllegalAccessException;

    @Operation(summary = "put user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "user put"),
            @ApiResponse(responseCode = "400", description = "validation error"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    UserDto putUser(Integer id, UserDto userDto);

    @Operation(summary = "get users in range")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "users found"),
            @ApiResponse(responseCode = "400", description = "validation error"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    List<UserDto> getUsersInRange(DateRangeDto dateRangeDto);
}
