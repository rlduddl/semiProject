package com.semiProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.semiProject.vo.StoreInfoVO;

@Mapper
public interface StoreMapper {
    List<StoreInfoVO> getAllStores();
}
