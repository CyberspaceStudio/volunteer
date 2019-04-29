package com.volunteer.volunteer.model;

public class Manager {
    private Integer managerId;

    private String managerName;

    private String managerPassword;

    private Integer managerPosition;

    private String department;

    private String school;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword == null ? null : managerPassword.trim();
    }

    public Integer getManagerPosition() {
        return managerPosition;
    }

    public void setManagerPosition(Integer managerPosition) {
        this.managerPosition = managerPosition;
    }
}