package com.volunteer.volunteer.model;

public class Praise {
    private Integer praiseId;

    private String praisePerson;

    private Integer activityId;

    private String department;

    public Integer getPraiseId() {
        return praiseId;
    }

    public void setPraiseId(Integer praiseId) {
        this.praiseId = praiseId;
    }

    public String getPraisePerson() {
        return praisePerson;
    }

    public void setPraisePerson(String praisePerson) {
        this.praisePerson = praisePerson == null ? null : praisePerson.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
}