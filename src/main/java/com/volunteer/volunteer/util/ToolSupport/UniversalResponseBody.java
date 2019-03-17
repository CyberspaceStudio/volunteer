package com.volunteer.volunteer.util.ToolSupport;

public class UniversalResponseBody<T> {
    private int errCode;

    private String msg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public UniversalResponseBody(int errCode, String msg, T data) {
        this.errCode = errCode;
        this.msg = msg;
        this.data = data;
    }

    public UniversalResponseBody(int errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;
}
