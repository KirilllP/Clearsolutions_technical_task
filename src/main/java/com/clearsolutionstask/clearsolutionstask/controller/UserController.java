package com.clearsolutionstask.clearsolutionstask.controller;

import com.clearsolutionstask.clearsolutionstask.dto.DateRangeDto;
import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import com.clearsolutionstask.clearsolutionstask.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @PostMapping("/user/save")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@Valid @RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @DeleteMapping("/user/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }

    @PostMapping("/user/patch/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto patchUser(@PathVariable Integer id, @Valid  @RequestBody PatchUserDto patchUserDto) throws IllegalAccessException {
        return userService.patchUser(id, patchUserDto);
    }

    @PostMapping("/user/put/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto putUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto){
        return userService.putUser(id, userDto);
    }

    @PostMapping("/user/range")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsersInRange(@Valid @RequestBody DateRangeDto dateRangeDto){
        return userService.getUsersInRange(dateRangeDto);
    }

}
