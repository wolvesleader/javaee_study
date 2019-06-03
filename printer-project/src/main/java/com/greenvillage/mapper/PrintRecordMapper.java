package com.greenvillage.mapper;

import com.greenvillage.pojo.PrintRecord;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-19
 */
public interface PrintRecordMapper {

    public List<PrintRecord> findAllPrintRecord();

    void deleteById(String id);
}
