package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.mapper.MarketingActivitiesMapper;
import com.fj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
