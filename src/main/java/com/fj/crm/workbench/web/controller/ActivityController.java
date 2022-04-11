package com.fj.crm.workbench.web.controller;

import com.fj.crm.commons.contants.Contants;
import com.fj.crm.commons.domain.ReturnObject;
import com.fj.crm.commons.utils.DateUtils;
import com.fj.crm.commons.utils.HSSFUtils;
import com.fj.crm.commons.utils.UUIDUtils;
import com.fj.crm.settings.domain.User;
import com.fj.crm.settings.service.impl.UserServiceImpl;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.service.impl.ActivityServiceImpl;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

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
    public String index(HttpServletRequest servletRequest) {
        //从后台获取所有用户信息，返回前台
        List<User> userList = userService.getAllUser();
        servletRequest.setAttribute("userList", userList);
        return "/workbench/activity/index";
    }

    /*
     * 创建市场活动
     * */
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(@RequestBody MarketingActivities activity, HttpServletRequest servletRequest) {
        //获取UUID
        activity.setId(UUIDUtils.getUUID());
        //获取当前登录的用户信息
        User user = (User) servletRequest.getSession().getAttribute(Contants.SESSION_USER);
        //市场活动的创建者和创建日期
        activity.setCreateBy(user.getId());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();

        try {
            //将市场活动存入数据库，并返回影响行数
            int res = activityService.createActivity(activity);

            if (res > 0) {
                //存储成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                //存储失败
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，稍后再试...");
            }
        } catch (Exception e) {
            //存储失败
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，稍后再试...");
        }
        return returnObject;
    }

    /*
     * 根据条件查询并返回市场活动
     * */
    @RequestMapping("/workbench/activity/queryActivitiesByConditionByPage.do")
    @ResponseBody
    public Object queryActivitiesByConditionByPage(@RequestBody Map<String, Object> map) {
        Map<String, Object> returnMap = new HashMap<>();
        //启动分页查询插件
        PageHelper.startPage((int) map.get("pageNo"), (int) map.get("pageSize"));
        //查询市场活动和总条数
        returnMap.put("activitiesList", activityService.queryActivitiesByConditionForPage(map));
        returnMap.put("activitiesTotal", activityService.queryTotalActivitiesByConditionForPage(map));
        return returnMap;
    }

    /*
     * 根据id删除指定市场活动
     * */
    @RequestMapping("/workbench/activity/deleteActivitiesByIDs.do")
    @ResponseBody
    public Object deleteActivitiesByIDs(@RequestBody Map<String, Object> map) {
        ReturnObject returnObject = new ReturnObject();
        try {
            Integer res = activityService.deleteActivitiesByIDs((List<String>) map.get("ids"));
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
     * 根据Id查询市场活动
     * */
    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(@RequestBody Map<String, Object> map) {
        return activityService.queryActivityById((String) map.get("id"));
    }

    /*
     * 修改指定市场活动
     * */
    @RequestMapping("/workbench/activity/updateActivityById.do")
    @ResponseBody
    public Object updateActivityById(@RequestBody MarketingActivities activity, HttpServletRequest request) {

        //设置市场活动的修改者和修改时间
        User curUser = (User) request.getSession().getAttribute(Contants.SESSION_USER);
        activity.setEditBy(curUser.getId());
        activity.setEditTime(DateUtils.formatDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        try {
            Integer res = activityService.updateActivityById(activity);
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
     * 导出所有市场活动，生成Excel文件
     * */
    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) {
        //设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //设置响应头，使浏览器接收到响应信息后，直接打开下载窗口，而不直接打开文件
        response.setHeader("Content-Disposition", "attachment;filename=ActivitiesList.xls");

        try (
                //调用service返回HSSFWorkbook对象
                HSSFWorkbook wb = activityService.writeActivitiesToExcel()
        ) {
            //获取输出流
            ServletOutputStream outputStream = response.getOutputStream();
            //将Excel文件传入输出流
            wb.write(outputStream);
            //刷新输出流
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 批量导入市场活动
    * */
    @RequestMapping("/workbench/activity/importBatchActivities.do")
    @ResponseBody
    public Object importBatchActivities(MultipartFile file,HttpServletRequest request){
        User curUser = (User) request.getSession().getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        List<MarketingActivities> activities = new ArrayList<>();
        try (
                InputStream is = file.getInputStream();
                HSSFWorkbook sheets = new HSSFWorkbook(is);
        ) {
            HSSFSheet sheet = sheets.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                MarketingActivities activity = new MarketingActivities();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(curUser.getName());
                activity.setCreateBy(curUser.getId());
                activity.setCreateTime(DateUtils.formatDateTime(new Date()));

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);

                    String cellValue = HSSFUtils.getCellValueForString(cell);

                    if (j == 0) {
                        activity.setName(cellValue);
                    } else if (j == 1) {
                        activity.setStartDate(cellValue);
                    } else if (j == 2) {
                        activity.setEndDate(cellValue);
                    } else if (j == 3) {
                        activity.setCost(cellValue);
                    } else if (j == 4) {
                        activity.setDescription(cellValue);
                    }
                }
                activities.add(activity);
            }
            Integer res = activityService.importFileToDatabase(activities);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setReturnData(res);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后重试...");
        }
        return returnObject;
    }
}
