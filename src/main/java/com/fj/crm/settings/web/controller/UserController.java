package com.fj.crm.settings.web.controller;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.commons.domain.ReturnObject;
import com.fj.crm.commons.utils.DateUtils;
import com.fj.crm.settings.domain.User;
import com.fj.crm.settings.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    /*
    * 用户登录
    * */
    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(@RequestBody Map<String,Object> map, HttpServletRequest request, HttpSession session, HttpServletResponse response){
        //根据账号和密码Map调用service方法查找User
        User user = userService.executeByUserActAndPwd(map);
        //创建返回对象
        ReturnObject returnObject = new ReturnObject();
        //数据库找不到相应user
        if(user == null){
            //登录失败，用户名或密码错误
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或密码错误！");
        }else {
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime()) > 0){
                //登录失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，账号已过期！");
            }else if("0".equals(user.getLockState())){
                //登录失败，账号已被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，账号已被锁定！");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，IP受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登录失败，IP受限！");
            }else{
                //登录成功，将user放入session
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                session.setAttribute(Contants.SESSION_USER,user);

                //判断用户是否记住密码
                if((boolean) map.get("checked")){
                    //如果需要记住密码，则设置账号和密码的Cookie
                    Cookie c1 = new Cookie("loginAct",user.getLoginAct());
                    c1.setMaxAge(10 * 24 * 60 * 60);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd",user.getLoginPwd());
                    c2.setMaxAge(10 * 24 * 60 * 60);
                    response.addCookie(c2);
                }else {
                    //如果用户不需要记住密码，则把原有Cookie销毁
                    Cookie c1 = new Cookie("loginAct","1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd","1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }
            }
        }
        //返回returnObject对象
        return returnObject;
    }

    /*
    * 用户登出
    * */
    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //Cookie销毁
        Cookie c1 = new Cookie("loginAct","1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //session销毁
        session.invalidate();
        //重定向到首页
        return "redirect:/";
    }
}
