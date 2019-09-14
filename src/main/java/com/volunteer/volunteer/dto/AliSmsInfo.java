package com.volunteer.volunteer.dto;

import lombok.Data;

/**
 * @author ：Maolin
 * @className ：AliSmsInfo
 * @date ：Created in 2019/9/14 12:09
 * @description： 阿里云短信通知info
 * @version: 1.0
 */
@Data
public class AliSmsInfo {
    private String name;

    private String department;

    private String activity;

    private String timeSlot;

    private String place;

    private String telNo;

    public AliSmsInfo(String name, String department, String activity, String timeSlot, String place, String telNo) {
        this.name = name;
        this.department = department;
        this.activity = activity;
        this.timeSlot = timeSlot;
        this.place = place;
        this.telNo = telNo;
    }
}
