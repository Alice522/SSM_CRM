package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.mapper.MarketingActivitiesMapper;
import com.fj.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private MarketingActivitiesMapper activitiesMapper;

    @Override
    public int createActivity(MarketingActivities activity) {
        return activitiesMapper.insertActivity(activity);
    }
}