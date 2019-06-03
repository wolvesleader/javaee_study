package com.greenvillage.service.impl;

import com.greenvillage.mapper.CustomPrinterOrderMapper;
import com.greenvillage.pojo.PrintOrder;
import com.greenvillage.service.CustomPrinterOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:quincy
 * Date:2019-03-25
 */
@Service
public class CustomPrinterOrderServiceImpl implements CustomPrinterOrderService {

    @Autowired
    private CustomPrinterOrderMapper customPrinterOrderMapper;

    @Override
    public void save(PrintOrder printerOrder) {
        customPrinterOrderMapper.save(printerOrder);
    }

    @Override
    public PrintOrder findOrderById(String id) {
        return customPrinterOrderMapper.findOrderById(id);
    }
}
