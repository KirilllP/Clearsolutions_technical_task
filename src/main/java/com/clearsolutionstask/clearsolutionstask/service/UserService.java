package com.clearsolutionstask.clearsolutionstask.service;

import com.clearsolutionstask.clearsolutionstask.dto.PatchUserDto;
import com.clearsolutionstask.clearsolutionstask.dto.UserDto;
import com.clearsolutionstask.clearsolutionstask.entity.User;
import com.clearsolutionstask.clearsolutionstask.mapper.UserMapper;
import com.clearsolutionstask.clearsolutionstask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto creatUser(UserDto userDto){
        User user = userMapper.toUser(userDto);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public UserDto patchUser(Integer id,  PatchUserDto patchUserDto) throws IllegalAccessException {

        User existingUser = userRepository.findById(id).get();

        User incompleteUser = userMapper.patchToUser(patchUserDto);

        Class<?> userClass = User.class;
        Field[] userFields = userClass.getDeclaredFields();

        for(Field field:userFields){

            field.setAccessible(true);
            Object value = field.get(incompleteUser);
            if(value!=null)
                field.set(existingUser, value);

        }

        return null;
    }

}
