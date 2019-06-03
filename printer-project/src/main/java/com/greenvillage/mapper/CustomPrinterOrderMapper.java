package com.greenvillage.mapper;

import com.greenvillage.pojo.PrintOrder;

/**
 * author:quincy
 * Date:2019-03-25
 */
public interface CustomPrinterOrderMapper {
    void save(PrintOrder printOrder);

    PrintOrder findOrderById(String id);
}
