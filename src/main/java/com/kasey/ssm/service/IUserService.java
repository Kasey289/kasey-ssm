package com.kasey.ssm.service;

import com.kasey.ssm.model.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    int addUser(User user);

    int removeUserById(int userId);
}
