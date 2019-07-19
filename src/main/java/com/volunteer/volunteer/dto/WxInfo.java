package com.volunteer.volunteer.dto;

import java.io.Serializable;

/**
 * @Author: MaoLin
 * @Date: 2019/3/28 17:18
 * @Version 1.0
 */
public class WxInfo implements Serializable {

    private String code;

    private String headPictureUrl;

    private String nickName;

    private String session_key;

    public WxInfo(String code, String headPictureUrl, String nickName) {
        this.code = code;
        this.headPictureUrl = headPictureUrl;
        this.nickName = nickName;
        this.session_key = null;
    }

    public WxInfo(String code, String headPictureUrl, String nickName, String session_key) {
        this.code = code;
        this.headPictureUrl = headPictureUrl;
        this.nickName = nickName;
        this.session_key = session_key;
    }

    public WxInfo(){
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHeadPictureUrl() {
        return headPictureUrl;
    }

    public void setHeadPictureUrl(String headPictureUrl) {
        this.headPictureUrl = headPictureUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    @Override
    public String toString() {
        return "WxInfo{" +
                "code='" + code + '\'' +
                ", headPictureUrl='" + headPictureUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", session_key='" + session_key + '\'' +
                '}';
    }
}
