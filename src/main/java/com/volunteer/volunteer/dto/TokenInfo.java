package com.volunteer.volunteer.dto;

/**
 * @author ：Maolin
 * @className ：TokenInfo
 * @date ：Created in 2019/7/19 17:53
 * @description： Token dto
 * @version: 1.0
 */
public class TokenInfo<T> {
    private T data;

    private String token;

    public TokenInfo(T data, String token) {
        this.data = data;
        this.token = token;
    }

    public TokenInfo(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
