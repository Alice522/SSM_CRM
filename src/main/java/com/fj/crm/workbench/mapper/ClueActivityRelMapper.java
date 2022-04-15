package com.fj.crm.workbench.mapper;

import com.fj.crm.workbench.domain.ClueActivityRel;

import java.util.List;

/**
 * @auther liuzhuochuan
 * @create 2022-04-15-18:29
 **/
public interface ClueActivityRelMapper {
    int insertClueAndActivityRelationship(List<ClueActivityRel> ClueActivityRels);

    int deleteClueActivity(String clueId,String activityId);
}
