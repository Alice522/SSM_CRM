package com.fj.crm.workbench.web.controller;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.commons.domain.ReturnObject;
import com.fj.crm.commons.utils.DateUtils;
import com.fj.crm.commons.utils.UUIDUtils;
import com.fj.crm.settings.domain.User;
import com.fj.crm.workbench.domain.ActivitiesRemark;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.service.impl.ActivitiesRemarkServiceImpl;
import com.fj.crm.workbench.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityRemarkController {
    @Autowired
    private ActivitiesRemarkServiceImpl activitiesRemarkService;

    @Autowired
    private ActivityServiceImpl activityService;

    @RequestMapping("/workbench/activity/queryActivityDetail/{id}")
    public String queryActivityDetail(@PathVariable("id") String ActivityId, Model model){
        MarketingActivities activity = activityService.queryActivityById(ActivityId);
        List<ActivitiesRemark> remarkList = activitiesRemarkService.queryRemarkByActivityId(ActivityId);

        model.addAttribute("activity",activity);
        model.addAttribute("remarkList",remarkList);

        return "workbench/activity/detail";
    }

    @RequestMapping("/workbench/activity/createActivityRemark.do")
    @ResponseBody
    public Object createActivityRemark(@RequestBody ActivitiesRemark remark, HttpServletRequest request){
        User curUser = (User) request.getSession().getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();

        remark.setId(UUIDUtils.getUUID());
        remark.setCreateBy(curUser.getId());
        remark.setCreateTime(DateUtils.formatDateTime(new Date()));
        try {
            int res = activitiesRemarkService.createActivityRemark(remark);
            if (res > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试...");
            }
        }catch (Exception e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/deleteActivityRemark.do")
    @ResponseBody
    public Object deleteActivityRemark(@RequestBody Map<String,String> map){
        ReturnObject returnObject = new ReturnObject();
        try {
            int res = activitiesRemarkService.deleteActivityRemark(map.get("remarkId"));
            if (res > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试...");
            }
        }catch (Exception e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityReMark.do")
    @ResponseBody
    public Object queryActivityReMark(@RequestBody Map<String,String> map){
        return activitiesRemarkService.queryRemarkById(map.get("remarkId"));
    }

    @RequestMapping("/workbench/activity/updateActivityReMark.do")
    @ResponseBody
    public Object updateActivityReMark(@RequestBody ActivitiesRemark remark,HttpServletRequest request) {
        User curUser = (User) request.getSession().getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        remark.setEditFlag("1");
        remark.setEditBy(curUser.getId());
        remark.setEditTime(DateUtils.formatDateTime(new Date()));

        try {
            int res = activitiesRemarkService.updateActivityRemark(remark);
            if (res > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试...");
            }
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }
        return returnObject;
    }
}
