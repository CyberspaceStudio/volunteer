package com.volunteer.volunteer.dto;

/**
 * @author ：Maolin
 * @className ：AccessToken
 * @date ：Created in 2019/7/26 19:32
 * @description：
 * @version: 1.0
 */
public class AccessToken {

    //接口访问凭证
    private String access_token;
    //接口有效期，单位：秒
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public AccessToken() {
    }

}

