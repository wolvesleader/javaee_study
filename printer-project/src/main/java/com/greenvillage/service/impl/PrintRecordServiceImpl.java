package com.greenvillage.service.impl;

import com.greenvillage.mapper.PrintRecordMapper;
import com.greenvillage.pojo.PrintRecord;
import com.greenvillage.service.PrintRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
@Service
public class PrintRecordServiceImpl implements PrintRecordService {


    @Autowired
    private PrintRecordMapper printRecordMapper;

    @Override
    public List<PrintRecord> findAllPrintRecord() {
        return printRecordMapper.findAllPrintRecord();
    }

    @Override
    public void deleteById(String id) {
        printRecordMapper.deleteById(id);
    }
}
