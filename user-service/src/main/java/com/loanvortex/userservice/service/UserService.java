package com.loanvortex.userservice.service;

import com.loanvortex.userservice.model.User;

public interface UserService {

    Iterable<User> getAllUsers();

    User getUserById(long id);

    User saveUser(User user);

    User editUser(User user);
}
