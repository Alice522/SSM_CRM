package com.fj.crm.workbench.web.controller;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.commons.domain.ReturnObject;
import com.fj.crm.commons.utils.UUIDUtils;
import com.fj.crm.workbench.domain.Clue;
import com.fj.crm.workbench.domain.ClueActivityRel;
import com.fj.crm.workbench.domain.ClueRemark;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.service.impl.ActivityServiceImpl;
import com.fj.crm.workbench.service.impl.ClueActivityRelServiceImpl;
import com.fj.crm.workbench.service.impl.ClueRemarkServiceImpl;
import com.fj.crm.workbench.service.impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class ClueRemarkController {
    @Autowired
    private ClueServiceImpl clueService;
    @Autowired
    private ActivityServiceImpl activityService;

    @Autowired
    private ClueRemarkServiceImpl clueRemarkService;

    @Autowired
    private ClueActivityRelServiceImpl clueActivityRelService;

    @RequestMapping("/workbench/clue/queryClueDetailById/{id}")
    public String queryClueDetailById(@PathVariable("id") String id, HttpServletRequest request) {
        Clue queryClue = clueService.queryClueById(id);
        List<ClueRemark> clueRemarks = clueRemarkService.queryClueRemarksByClueId(id);
        List<MarketingActivities> activities = clueRemarkService.queryActivitiesByClueId(id);
        request.setAttribute("queryClue", queryClue);
        request.setAttribute("clueRemarks", clueRemarks);
        request.setAttribute("activities", activities);
        return "/workbench/clue/detail";
    }

    /*
     * 根据name模糊查询市场活动
     * */
    @RequestMapping(value = {"/workbench/clue/queryActivitiesByNameLike/{name}",
            "/workbench/clue/queryActivitiesByNameLike/"})
    @ResponseBody
    public Object queryActivitiesByNameLike(@PathVariable(value = "name", required = false) String name) {
        if (name == null) name = "";
        return activityService.queryActivitiesByNameLike(name);
    }

    /*
     * 添加线索和市场活动关联
     * */
    @RequestMapping("/workbench/clue/createClueAndActivityRel.do")
    @ResponseBody
    public Object createClueAndActivityRel(@RequestBody Map<String, Object> map) {
        ReturnObject returnObject = new ReturnObject();
        String clueId = (String) map.get("clueId");
        List<String> innerActivityIds = clueRemarkService.queryActivitiesIdByClueId(clueId);
        List<String> outerActivityIds = (List<String>) map.get("activities");
        List<ClueActivityRel> clueActivityRels = new ArrayList<>();
        ClueActivityRel clueActivityRel;
        Iterator<String> iterator = outerActivityIds.iterator();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            if (innerActivityIds.contains(temp)) {
                iterator.remove();
            }
        }
        for (String s : outerActivityIds) {
            clueActivityRel = new ClueActivityRel();
            clueActivityRel.setId(UUIDUtils.getUUID());
            clueActivityRel.setClueId(clueId);
            clueActivityRel.setActivityId(s);
            clueActivityRels.add(clueActivityRel);
        }

        try {
            int res = clueActivityRelService.createClueActivityRel(clueActivityRels);
            if (res > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，稍后再试...");
            }
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，稍后再试...");
        }
        return returnObject;
    }

    /*
     * 删除线索和市场活动关联
     * */
    @RequestMapping("/workbench/clue/deleteClueActivity/{clueId}/{activityId}")
    @ResponseBody
    public Object deleteClueActivity(@PathVariable("clueId") String clueId, @PathVariable("activityId") String activityId) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int res = clueRemarkService.deleteClueActivity(clueId, activityId);
            if (res > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，稍后再试...");
            }
        } catch (Exception e) {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，稍后再试...");
        }
        return returnObject;
    }
}
