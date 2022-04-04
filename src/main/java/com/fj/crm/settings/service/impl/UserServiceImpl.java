package com.fj.crm.settings.service.impl;

import com.fj.crm.settings.domain.User;
import com.fj.crm.settings.mapper.UserMapper;
import com.fj.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User executeByUserActAndPwd(Map<String, Object> map) {
        return userMapper.queryByUserActAndPwd(map);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.queryAllUser();
    }
}
