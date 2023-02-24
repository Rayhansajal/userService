package com.user.service.userService.services;

import com.user.service.userService.entity.User;

import java.util.List;

public interface UserService {

    // create user
    User saveUser(User user);

    // get all user

    List<User>getAllUser();

    // get single user
    User getUser(String userId);

    // update user

    User updateUser(User user);

    // delete user
    User deleteUser(String userId);
}
