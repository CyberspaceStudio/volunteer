package com.volunteer.volunteer.model;

public class Manager {
    private Integer manageId;

    private String managePwd;

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public String getManagePwd() {
        return managePwd;
    }

    public void setManagePwd(String managePwd) {
        this.managePwd = managePwd == null ? null : managePwd.trim();
    }
}