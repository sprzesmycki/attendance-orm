package com.pgssoft.controllers.converter;

import com.pgssoft.dto.ActivityDto;
import com.pgssoft.dto.UserDto;
import com.pgssoft.model.Activity;
import com.pgssoft.model.User;

import java.util.stream.Collectors;

/**
 * Created by andrzej on 19.03.17.
 */
public class Converter {
    public static User fromUserDto(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setId(user.getId());
        userDto.setRole(user.getRole().toString());
        return userDto;
    }

    public static ActivityDto toActivityDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setName(activity.getName());
        activityDto.setStartDate(activity.getStartDate());
        return activityDto;
    }
}
