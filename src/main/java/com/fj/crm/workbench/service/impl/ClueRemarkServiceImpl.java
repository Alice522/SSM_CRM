package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.ClueRemark;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.mapper.ClueActivityRelMapper;
import com.fj.crm.workbench.mapper.ClueRemarkMapper;
import com.fj.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueRemarkServiceImpl implements ClueRemarkService{
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private ClueActivityRelMapper clueActivityRelMapper;

    @Override
    public List<ClueRemark> queryClueRemarksByClueId(String id) {
        return clueRemarkMapper.selectClueRemarksByClueId(id);
    }

    @Override
    public List<MarketingActivities> queryActivitiesByClueId(String id) {
        return clueRemarkMapper.selectActivitiesByClueId(id);
    }

    @Override
    public List<String> queryActivitiesIdByClueId(String id) {
        return clueRemarkMapper.selectActivitiesIdByClueId(id);
    }

    @Override
    public int deleteClueActivity(String clueId, String activityId) {
        return clueActivityRelMapper.deleteClueActivity(clueId,activityId);
    }
}
