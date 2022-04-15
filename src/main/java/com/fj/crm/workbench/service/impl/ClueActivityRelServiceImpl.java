package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.ClueActivityRel;
import com.fj.crm.workbench.mapper.ClueActivityRelMapper;
import com.fj.crm.workbench.service.ClueActivityRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther liuzhuochuan
 * @create 2022-04-15-18:44
 **/
@Service
public class ClueActivityRelServiceImpl implements ClueActivityRelService {
    @Autowired
    private ClueActivityRelMapper clueActivityRelMapper;
    @Override
    public int createClueActivityRel(List<ClueActivityRel> clueActivityRels) {
        return clueActivityRelMapper.insertClueAndActivityRelationship(clueActivityRels);
    }
}
