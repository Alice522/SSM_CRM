package com.fj.crm.settings.service;

import com.fj.crm.settings.domain.User;

import java.util.Map;

public interface UserService {

    User executeByUserActAndPwd(Map<String,Object> map);
}
