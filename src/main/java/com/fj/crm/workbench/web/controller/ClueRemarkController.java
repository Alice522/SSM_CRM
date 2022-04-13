package com.fj.crm.workbench.web.controller;

import com.fj.crm.workbench.domain.Clue;
import com.fj.crm.workbench.domain.ClueRemark;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.service.impl.ClueRemarkServiceImpl;
import com.fj.crm.workbench.service.impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClueRemarkController {
    @Autowired
    private ClueServiceImpl clueService;

    @Autowired
    private ClueRemarkServiceImpl clueRemarkService;

    @RequestMapping("/workbench/clue/queryClueDetailById/{id}")
    public String queryClueDetailById(@PathVariable("id") String id, HttpServletRequest request){
        Clue queryClue = clueService.queryClueById(id);
        List<ClueRemark> clueRemarks = clueRemarkService.queryClueRemarksByClueId(id);
        List<MarketingActivities> activities = clueRemarkService.queryActivitiesByClueId(id);
        request.setAttribute("queryClue",queryClue);
        request.setAttribute("clueRemarks",clueRemarks);
        request.setAttribute("activities",activities);
        return "/workbench/clue/detail";
    }
}
