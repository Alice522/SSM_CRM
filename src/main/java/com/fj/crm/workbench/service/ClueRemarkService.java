package com.fj.crm.workbench.service;

import com.fj.crm.workbench.domain.ClueRemark;
import com.fj.crm.workbench.domain.MarketingActivities;

import java.util.List;

public interface ClueRemarkService {

    List<ClueRemark> queryClueRemarksByClueId(String id);

    List<MarketingActivities> queryActivitiesByClueId(String id);
}
