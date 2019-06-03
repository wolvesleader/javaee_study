package com.greenvillage.service.impl;

import com.greenvillage.mapper.FeeScaleMapper;
import com.greenvillage.pojo.FeeScale;
import com.greenvillage.pojo.PrintOrder;
import com.greenvillage.service.FeeScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@Service
public class FeeScaleServiceImpl implements FeeScaleService {


    @Autowired
    private FeeScaleMapper feeScaleMapper;

    @Override
    public List<FeeScale> findAllFeeScale() {
        return feeScaleMapper.findAllFeeScale();
    }

    @Override
    public FeeScale findById(String id) {
        return feeScaleMapper.findById(id);
    }

    @Override
    public int updateById(FeeScale feeScale) {
        return feeScaleMapper.updateById(feeScale);
    }

    @Override
    public void deleteById(String id) {
        feeScaleMapper.deleteById(id);
    }

    @Override
    public FeeScale findFeeScaleByCondition(PrintOrder printOrder) {
        
        return feeScaleMapper.findFeeScaleByCondition(printOrder);
    }
}
