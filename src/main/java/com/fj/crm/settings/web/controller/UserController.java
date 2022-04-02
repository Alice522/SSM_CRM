package com.fj.crm.settings.web.controller;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.commons.domain.ReturnObject;
import com.fj.crm.commons.utils.DateUtils;
import com.fj.crm.settings.domain.User;
import com.fj.crm.settings.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("settings/qx/user/login.do")
    @ResponseBody
    public Object login(@RequestBody Map<String,Object> map, HttpServletRequest request){
        User user = userService.executeByUserActAndPwd(map);
        ReturnObject returnObject = new ReturnObject();

        if(user == null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或密码错误！");
        }else {
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime()) > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，账号已过期！");
            }else if("0".equals(user.getLockState())){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，账号已被锁定！");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，IP受限！");
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }
        }
        return returnObject;
    }
}
