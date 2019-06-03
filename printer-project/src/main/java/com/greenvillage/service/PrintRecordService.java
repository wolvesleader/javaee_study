package com.greenvillage.service;

import com.greenvillage.pojo.PrintRecord;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
public interface PrintRecordService {

    public List<PrintRecord> findAllPrintRecord();

    void deleteById(String id);
}
