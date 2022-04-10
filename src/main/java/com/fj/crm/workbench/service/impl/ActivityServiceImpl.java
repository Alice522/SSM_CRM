package com.fj.crm.workbench.service.impl;

import com.fj.crm.commons.utils.UUIDUtils;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.mapper.MarketingActivitiesMapper;
import com.fj.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private MarketingActivitiesMapper activitiesMapper;

    @Override
    public int createActivity(MarketingActivities activity) {
        return activitiesMapper.insertActivity(activity);
    }

    @Override
    public List<MarketingActivities> queryActivitiesByConditionForPage(Map<String, Object> map) {
        return activitiesMapper.selectActivitiesByConditionForPage(map);
    }

    @Override
    public Integer queryTotalActivitiesByConditionForPage(Map<String, Object> map) {
        return activitiesMapper.selectTotalActivitiesByCondition(map);
    }

    @Override
    public Integer deleteActivitiesByIDs(List<String> ids) {
        return activitiesMapper.deleteActivitiesByIDs(ids);
    }

    @Override
    public MarketingActivities queryActivityById(String id) {
        return activitiesMapper.queryActivityById(id);
    }

    @Override
    public Integer updateActivityById(MarketingActivities activity) {
        return activitiesMapper.updateActivityById(activity);
    }

    @Override
    public HSSFWorkbook writeActivitiesToExcel() {
        //调用mapper得到所有市场活动的List
        List<MarketingActivities> activitiesList = activitiesMapper.queryAllActivities();

        //创建一个Excel对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建市场活动表对象
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        //生成表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建者");
        cell = row.createCell(8);
        cell.setCellValue("修改者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");

        int len = activitiesList.size();
        if (len != 0) {
            MarketingActivities activity = null;
            for (int i = 0; i < len; i++) {
                activity = activitiesList.get(i);
                row = sheet.createRow(i + 1);

                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(8);
                cell.setCellValue(activity.getEditBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
            }
        }
        return wb;
    }

    @Override
    public Integer importFileToDatabase(List<MarketingActivities> activities) {
        return activitiesMapper.insertBatchActivities(activities);
    }
}
