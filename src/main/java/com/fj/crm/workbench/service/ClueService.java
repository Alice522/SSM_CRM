package com.fj.crm.workbench.service;

import com.fj.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {

    int createClue(Clue clue);

    List<Clue> queryCluesByCondition(Map<String,Object> map);

    Integer queryCluesByConditionGetTotal(Map<String,Object> map);

    Clue queryClueById(String id);
}
