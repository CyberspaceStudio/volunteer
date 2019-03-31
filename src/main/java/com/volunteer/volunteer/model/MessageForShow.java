package com.volunteer.volunteer.model;

import java.util.Date;

public class MessageForShow {
    private Integer activityId;

    private String informationName;

    private String department;

    private Date activityTime;

    private String content;

    private Integer readingVolume;

    private Integer mainId;

    private String portraitUrl;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName == null ? null : informationName.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReadingVolume() {
        return readingVolume;
    }

    public void setReadingVolume(Integer readingVolume) {
        this.readingVolume = readingVolume;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl == null ? null : portraitUrl.trim();
    }
}