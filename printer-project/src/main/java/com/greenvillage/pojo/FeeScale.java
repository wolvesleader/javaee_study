package com.greenvillage.pojo;

/**
 * author:quincy
 * Date:2019-03-18
 */
public class FeeScale {

    private Long id;
    private String payMoney;
    private String printerSize;
    private String printerColor;
    private String printerFace;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPrinterSize() {
        return printerSize;
    }

    public void setPrinterSize(String printerSize) {
        this.printerSize = printerSize;
    }

    public String getPrinterColor() {
        return printerColor;
    }

    public void setPrinterColor(String printerColor) {
        this.printerColor = printerColor;
    }

    public String getPrinterFace() {
        return printerFace;
    }

    public void setPrinterFace(String printerFace) {
        this.printerFace = printerFace;
    }
}
