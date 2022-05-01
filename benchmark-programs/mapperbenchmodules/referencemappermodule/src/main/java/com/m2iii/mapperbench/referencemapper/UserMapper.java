package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.AccountStatus;
import com.m2iii.mapperbench.model.destination.UserDTO;
import com.m2iii.mapperbench.model.source.User;

public class UserMapper implements Converter<User, UserDTO> {

    private static UserMapper userMapper = null;

    private UserMapper() {}

    @Override
    public UserDTO convert(User user) {
        return user == null ? null : new UserDTO(
                user.getUsername(),
                user.getEmail(),
                AccountStatus.valueOf(user.getUserAccountStatus().name())
        );
    }

    public static UserMapper getInstance() {
        if (userMapper == null) {
            userMapper = new UserMapper();
        }
        return userMapper;
    }
}
