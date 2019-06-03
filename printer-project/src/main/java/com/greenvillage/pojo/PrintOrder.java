package com.greenvillage.pojo;

/**
 * author:quincy
 * Date:2019-03-25
 */
public class PrintOrder {

    private Long id;
    private String printerUrl;
    private String printerNumber;
    private String printerSize;
    private String printerColor;
    private String printerFace;

    private String totalOrderMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrinterUrl() {
        return printerUrl;
    }

    public void setPrinterUrl(String printerUrl) {
        this.printerUrl = printerUrl;
    }

    public String getPrinterNumber() {
        return printerNumber;
    }

    public void setPrinterNumber(String printerNumber) {
        this.printerNumber = printerNumber;
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

    public String getTotalOrderMoney() {
        return totalOrderMoney;
    }

    public void setTotalOrderMoney(String totalOrderMoney) {
        this.totalOrderMoney = totalOrderMoney;
    }

    @Override
    public String toString() {
        return "PrintOrder{" +
                "id=" + id +
                ", printerUrl='" + printerUrl + '\'' +
                ", printerNumber='" + printerNumber + '\'' +
                ", printerSize='" + printerSize + '\'' +
                ", printerColor='" + printerColor + '\'' +
                ", printerFace='" + printerFace + '\'' +
                ", totalOrderMoney='" + totalOrderMoney + '\'' +
                '}';
    }
}
