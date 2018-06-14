package com.kasey.ssm.dao;


import com.kasey.ssm.mapper.IUserMapper;
import com.kasey.ssm.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserMapperTest {

    Logger logger = LoggerFactory.getLogger(IUserMapperTest.class);

    @Autowired
    private IUserMapper userMapper;

    @Test
    public void testFindAll() throws Exception {
        List<User> userlist = userMapper.findAll();

        for(User user:userlist){
            logger.info("查询结果是:{}",user);
        }

    }


    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setName("无名");
        user.setPassword("123444");
        user.setSex("男");
        user.setDesc("勤劳");
        int i = userMapper.addUser(user);

        if(i > 0){
            logger.info("新增成功:{}",i);
        }else{
            logger.info("新增失败:{}",i);
        }


    }
}
