package com.fj.crm.workbench.service;

import com.fj.crm.workbench.domain.MarketingActivities;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int createActivity(MarketingActivities activity);

    List<MarketingActivities> queryActivitiesByConditionForPage(Map<String,Object> map);

    Integer queryTotalActivitiesByConditionForPage(Map<String,Object> map);

    Integer deleteActivitiesByIDs(List<String> ids);

    MarketingActivities queryActivityById(String id);

    Integer updateActivityById(MarketingActivities activity);

    HSSFWorkbook writeActivitiesToExcel();
}
