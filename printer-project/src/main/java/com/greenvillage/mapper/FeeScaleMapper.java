package com.greenvillage.mapper;

import com.greenvillage.pojo.FeeScale;
import com.greenvillage.pojo.PrintOrder;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
public interface FeeScaleMapper {

    public List<FeeScale> findAllFeeScale();

    FeeScale findById(String id);

    int updateById(FeeScale feeScale);

    void deleteById(String id);

    FeeScale findFeeScaleByCondition(PrintOrder printOrder);
}
