package com.greenvillage.service.impl;

import com.greenvillage.mapper.PrintPointMapper;
import com.greenvillage.pojo.PrintPoint;
import com.greenvillage.service.PrintPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */

@Service
public class PrintPointServiceImpl implements PrintPointService {

    @Autowired
    private PrintPointMapper printPointMapper;

    @Override
    public List<PrintPoint> findAllPrintPoint() {
        List<PrintPoint> allPrintPoint = printPointMapper.findAllPrintPoint();
        return allPrintPoint;
    }

    @Override
    public PrintPoint findById(String id) {
        return printPointMapper.findById(id);
    }

    @Override
    public int updateById(PrintPoint printPoint) {
        return printPointMapper.updateById(printPoint);
    }

    @Override
    public void deleteById(String id) {
        printPointMapper.deleteById(id);
    }

    @Override
    public PrintPoint findByPrinterNumberAndPassword(String printerNumber, String password) {
        return printPointMapper.findByPrinterNumberAndPassword(printerNumber,password);
    }
}
