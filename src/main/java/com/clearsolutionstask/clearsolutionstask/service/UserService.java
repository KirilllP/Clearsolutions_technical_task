package com.clearsolutionstask.clearsolutionstask.service;

import com.clearsolutionstask.clearsolutionstask.dto.DateRangeDto;
import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import com.clearsolutionstask.clearsolutionstask.exception.customException.WrongIdException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    /**
     * Saves a new user.
     *
     * @param userDto The user data to be saved.
     * @return The saved user data.
     */
    UserDto saveUser(UserDto userDto);

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     * @throws WrongIdException if no user with the given ID exists.
     */
    void deleteUser(@PathVariable Integer id);

    /**
     * Updates a user partially with the provided data.
     *
     * @param id The ID of the user to update.
     * @param patchUserDto The partial user data for update.
     * @return The updated user data.
     * @throws WrongIdException if no user with the given ID exists.
     * @throws IllegalAccessException if access to a field is denied.
     */
    UserDto patchUser(@PathVariable Integer id, @Valid @RequestBody PatchUserDto patchUserDto) throws IllegalAccessException;

    /**
     * Updates a user completely with the provided data.
     *
     * @param id The ID of the user to update.
     * @param userDto The complete user data for update.
     * @return The updated user data.
     */
    UserDto putUser(Integer id, UserDto userDto);

    /**
     * Retrieves users within a specified date range.
     *
     * @param dateRangeDto The date range to filter users.
     * @return A list of users within the specified date range.
     */
    List<UserDto> getUsersInRange(DateRangeDto dateRangeDto);
}
