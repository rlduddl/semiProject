package com.semiProject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.semiProject.mapper.StoreMapper;
import com.semiProject.service.StoreService;
import com.semiProject.vo.StoreInfoVO;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List<StoreInfoVO> getAllStores() {
        return storeMapper.getAllStores();
    }
}