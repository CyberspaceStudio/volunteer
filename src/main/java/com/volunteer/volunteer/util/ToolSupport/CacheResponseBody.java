package com.volunteer.volunteer.util.ToolSupport;

/**
 * @description: 用于缓存响应
 * @author: maolin
 * @create: 2019-04-16 17:18
 **/
public class CacheResponseBody<T> {
    private int errCode;

    private String key;

    private T value;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public CacheResponseBody(int errCode, String key, T value) {
        this.errCode = errCode;
        this.key = key;
        this.value = value;
    }

}
