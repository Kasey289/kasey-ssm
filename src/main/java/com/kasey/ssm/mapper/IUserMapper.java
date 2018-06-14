package com.kasey.ssm.mapper;

import com.kasey.ssm.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMapper {
    List<User> findAll();

    int addUser(User user);

    int removeUserById(@Param("userId") int userId);
}
