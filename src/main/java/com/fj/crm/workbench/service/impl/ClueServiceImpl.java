package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.Clue;
import com.fj.crm.workbench.mapper.ClueMapper;
import com.fj.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Override
    public int createClue(Clue clue) {
        return clueMapper.insert(clue);
    }

    @Override
    public List<Clue> queryCluesByCondition(Map<String, Object> map) {
        return clueMapper.selectCluesByCondition(map);
    }

    @Override
    public Integer queryCluesByConditionGetTotal(Map<String, Object> map) {
        return clueMapper.selectCluesByConditionGetTotal(map);
    }

    @Override
    public Clue queryClueById(String id) {
        return clueMapper.selectClueById(id);
    }
}
