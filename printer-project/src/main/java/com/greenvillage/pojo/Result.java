package com.greenvillage.pojo;

/**
 * author:quincy
 * Date:2019-03-18
 */
public class Result<T> {

    private String message = "成功";

    private String statusCode = "200";

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
