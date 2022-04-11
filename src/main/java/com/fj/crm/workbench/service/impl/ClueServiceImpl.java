package com.fj.crm.workbench.service.impl;

import com.fj.crm.workbench.domain.Clue;
import com.fj.crm.workbench.mapper.ClueMapper;
import com.fj.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Override
    public int createClue(Clue clue) {
        return clueMapper.insert(clue);
    }
}
