package com.volunteer.volunteer.enums;


import java.util.HashMap;
import java.util.Map;

public enum LikeStatusEnum {
    LIKE(1,"点赞"),UNLIKE(0,"未点赞");

    private int code;

    private String value;

    private static final Map<Integer ,LikeStatusEnum> map = new HashMap<>();

    LikeStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Map<Integer, LikeStatusEnum> getMap() {
        return map;
    }
}
