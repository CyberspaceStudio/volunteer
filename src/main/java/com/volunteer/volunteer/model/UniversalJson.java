package com.volunteer.volunteer.model;
/*
   通用返回类
   含有
   @Param data 使用含有各种实体类中变量
   @Param error_code 各种出错码
   @Param msg 返回是否成功
 */
public class UniversalJson<T> {
    private int error_code;

    private String msg;

    private T data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
