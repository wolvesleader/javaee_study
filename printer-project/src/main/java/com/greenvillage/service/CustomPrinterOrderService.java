package com.greenvillage.service;

import com.greenvillage.pojo.PrintOrder;

/**
 * author:quincy
 * Date:2019-03-25
 */
public interface CustomPrinterOrderService {
    void save(PrintOrder printerOrder);

    PrintOrder findOrderById(String id);
}
