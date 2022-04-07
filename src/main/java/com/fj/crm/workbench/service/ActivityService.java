package com.fj.crm.workbench.service;

import com.fj.crm.workbench.domain.MarketingActivities;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int createActivity(MarketingActivities activity);

    List<MarketingActivities> queryActivitiesByConditionForPage(Map<String,Object> map);

    Integer queryTotalActivitiesByConditionForPage(Map<String,Object> map);
}
