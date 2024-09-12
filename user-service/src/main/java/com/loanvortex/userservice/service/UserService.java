package com.loanstorm.userservice.service;

import com.loanstorm.userservice.model.User;

public interface UserService {

    Iterable<User> getAllUsers();

    User getUserById(long id);

    User saveUser(User user);

    User editUser(User user);
}
