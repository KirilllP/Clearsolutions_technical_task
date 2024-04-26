package com.clearsolutionstask.clearsolutionstask.service;

import com.clearsolutionstask.clearsolutionstask.dto.DateRangeDto;
import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import com.clearsolutionstask.clearsolutionstask.entity.User;
import com.clearsolutionstask.clearsolutionstask.exception.customException.WrongIdException;
import com.clearsolutionstask.clearsolutionstask.mapper.UserMapper;
import com.clearsolutionstask.clearsolutionstask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        Long numberOfDeleted = userRepository.deleteAllById(id);

        if (numberOfDeleted == 0) throw new WrongIdException("User with such ID does not exist");
    }

    @Override
    public UserDto patchUser(Integer id, PatchUserDto patchUserDto) throws IllegalAccessException {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new WrongIdException("User with such ID does not exist"));
        User incompleteUser = userMapper.patchToUser(patchUserDto);

        Class<?> userClass = User.class;
        Field[] userFields = userClass.getDeclaredFields();

        for (Field field : userFields) {
            field.setAccessible(true);
            Object value = field.get(incompleteUser);
            if (value != null)
                field.set(existingUser, value);
        }

        return userMapper.toUserDto(userRepository.save(existingUser));
    }

    @Override
    public UserDto putUser(Integer id, UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setId(id);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getUsersInRange(DateRangeDto dateRangeDto) {
        return userRepository.findAllByBirthDateBetween(dateRangeDto.getFrom(), dateRangeDto.getTo())
                .stream().map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
