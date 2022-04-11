package com.fj.crm.workbench.web.controller;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.commons.domain.ReturnObject;
import com.fj.crm.commons.utils.DateUtils;
import com.fj.crm.commons.utils.UUIDUtils;
import com.fj.crm.settings.domain.User;
import com.fj.crm.settings.service.impl.UserServiceImpl;
import com.fj.crm.workbench.domain.Clue;
import com.fj.crm.workbench.service.impl.ClueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class ClueController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ClueServiceImpl clueService;

    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest servletRequest){
        //从后台获取所有用户信息，返回前台
        List<User> userList = userService.getAllUser();
        servletRequest.setAttribute("userList", userList);
        return "/workbench/clue/index";
    }

    @RequestMapping("/workbench/clue/createClue.do")
    @ResponseBody
    public Object createClue(@RequestBody Clue clue, HttpServletRequest request){
        User curUser = (User) request.getSession().getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();

        clue.setId(UUIDUtils.getUUID());
        clue.setCreateBy(curUser.getId());
        clue.setCreateTime(DateUtils.formatDateTime(new Date()));

        try {
            int res = clueService.createClue(clue);
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
