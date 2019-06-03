package com.greenvillage.pojo;

import java.util.Date;

/**
 * author:quincy
 * Date:2019-03-19
 */
public class PrintRecord {

    private Long id;
    private String printPointName;
    private String printFileName;
    private Date printTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrintPointName() {
        return printPointName;
    }

    public void setPrintPointName(String printPointName) {
        this.printPointName = printPointName;
    }

    public String getPrintFileName() {
        return printFileName;
    }

    public void setPrintFileName(String printFileName) {
        this.printFileName = printFileName;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }
}
