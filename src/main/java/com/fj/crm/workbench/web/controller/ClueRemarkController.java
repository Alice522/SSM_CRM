package com.fj.crm.workbench.web.controller;

import com.fj.crm.workbench.domain.Clue;
import com.fj.crm.workbench.service.impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClueRemarkController {
    @Autowired
    private ClueServiceImpl clueService;

    @RequestMapping("/workbench/clue/queryClueDetailById/{id}")
    public String queryClueDetailById(@PathVariable("id") String id, HttpServletRequest request){
        Clue queryClue = clueService.queryClueById(id);
        request.setAttribute("queryClue",queryClue);
        return "/workbench/clue/detail";
    }
}
