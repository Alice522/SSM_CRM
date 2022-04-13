package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.ClueRemark;
import com.fj.crm.workbench.domain.MarketingActivities;
import com.fj.crm.workbench.mapper.ClueRemarkMapper;
import com.fj.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueRemarkServiceImpl implements ClueRemarkService{
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarksByClueId(String id) {
        return clueRemarkMapper.selectClueRemarksByClueId(id);
    }

    @Override
    public List<MarketingActivities> queryActivitiesByClueId(String id) {
        return clueRemarkMapper.selectActivitiesByClueId(id);
    }
}
