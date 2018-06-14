package com.kasey.ssm.service.impl;

import com.kasey.ssm.mapper.IUserMapper;
import com.kasey.ssm.model.User;
import com.kasey.ssm.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserMapper userMapper;

    @Override
    public List<User> findAll() {
        return this.userMapper.findAll();
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int removeUserById(int userId) {
        return userMapper.removeUserById(userId);
    }
}
