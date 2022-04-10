package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.ActivitiesRemark;
import com.fj.crm.workbench.mapper.ActivitiesRemarkMapper;
import com.fj.crm.workbench.service.ActivitiesRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivitiesRemarkServiceImpl implements ActivitiesRemarkService {
    @Autowired
    private ActivitiesRemarkMapper activitiesRemarkMapper;

    @Override
    public List<ActivitiesRemark> queryRemarkByActivityId(String id) {
        return activitiesRemarkMapper.selectRemarkByActivityId(id);
    }

    @Override
    public int createActivityRemark(ActivitiesRemark remark) {
        return activitiesRemarkMapper.insert(remark);
    }

    @Override
    public int deleteActivityRemark(String id) {
        return activitiesRemarkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ActivitiesRemark queryRemarkById(String id) {
        return activitiesRemarkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateActivityRemark(ActivitiesRemark remark) {
        return activitiesRemarkMapper.updateByPrimaryKeySelective(remark);
    }
}
