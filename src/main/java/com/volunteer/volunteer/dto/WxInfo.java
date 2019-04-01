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

    private String falseName;

    public WxInfo(String code, String headPictureUrl, String falseName) {
        this.code = code;
        this.headPictureUrl = headPictureUrl;
        this.falseName = falseName;
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

    public String getFalseName() {
        return falseName;
    }

    public void setFalseName(String falseName) {
        this.falseName = falseName;
    }

    @Override
    public String toString() {
        return "WxInfo{" +
                "code='" + code + '\'' +
                ", headPictureUrl='" + headPictureUrl + '\'' +
                ", falseName='" + falseName + '\'' +
                '}';
    }
}
