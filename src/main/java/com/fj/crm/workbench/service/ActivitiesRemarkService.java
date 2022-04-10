package com.fj.crm.workbench.service;

import com.fj.crm.workbench.domain.ActivitiesRemark;

import java.util.List;

public interface ActivitiesRemarkService {
    List<ActivitiesRemark> queryRemarkByActivityId(String id);

    int createActivityRemark(ActivitiesRemark remark);

    int deleteActivityRemark(String id);

    ActivitiesRemark queryRemarkById(String id);

    int updateActivityRemark(ActivitiesRemark remark);
}
