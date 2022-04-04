package com.fj.crm.workbench.web.controller;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.commons.domain.ReturnObject;
import com.fj.crm.commons.utils.DateUtils;
import com.fj.crm.commons.utils.UUIDUtils;
import com.fj.crm.settings.domain.User;
import com.fj.crm.settings.service.impl.UserServiceImpl;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ActivityServiceImpl activityService;

    /*
    * 市场活动主页面
    * */
    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest servletRequest){
        //从后台获取所有用户信息，返回前台
        List<User> userList = userService.getAllUser();
        servletRequest.setAttribute("userList",userList);
        return "/workbench/activity/index";
    }

    /*
    * 创建市场活动
    * */
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(@RequestBody MarketingActivities activity,HttpServletRequest servletRequest){
        //获取UUID
        activity.setId(UUIDUtils.getUUID());
        //获取当前登录的用户信息
        User user =(User) servletRequest.getSession().getAttribute(Contants.SESSION_USER);
        //市场活动的创建者和创建日期
        activity.setCreateBy(user.getId());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();

        try {
            //将市场活动存入数据库，并返回影响行数
            int res = activityService.createActivity(activity);

            if(res > 0){
                //存储成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                //存储失败
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，稍后再试...");
            }
        }catch (Exception e){
            //存储失败
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，稍后再试...");
        }
    return returnObject;
    }
}
