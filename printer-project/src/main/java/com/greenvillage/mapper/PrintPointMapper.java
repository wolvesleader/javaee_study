package com.greenvillage.mapper;

import com.greenvillage.pojo.PrintPoint;

import java.util.List;

/**
 * author:quincy
 * Date:2019-03-18
 */
public interface PrintPointMapper {


    public List<PrintPoint> findAllPrintPoint();

    PrintPoint findById(String id);

    int updateById(PrintPoint printPoint);

    void deleteById(String id);

    PrintPoint findByPrinterNumberAndPassword(String printerNumber, String password);
}
