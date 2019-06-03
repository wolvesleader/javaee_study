package com.greenvillage.pojo;

/**
 * author:quincy
 * Date:2019-03-19
 */
public class Setting {

    private Long id;
    private String backUserName;
    private String backPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBackUserName() {
        return backUserName;
    }

    public void setBackUserName(String backUserName) {
        this.backUserName = backUserName;
    }

    public String getBackPassword() {
        return backPassword;
    }

    public void setBackPassword(String backPassword) {
        this.backPassword = backPassword;
    }
}
