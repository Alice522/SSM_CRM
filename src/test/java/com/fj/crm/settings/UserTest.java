package com.fj.crm.settings;

import com.fj.crm.settings.domain.User;
import com.fj.crm.settings.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void userTest(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("act","ls");
        map.put("pwd","yf123");
        User user = userService.executeByUserActAndPwd(map);
        System.out.println(user);
    }
}
