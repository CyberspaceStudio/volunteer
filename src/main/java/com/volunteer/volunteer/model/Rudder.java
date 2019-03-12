package com.volunteer.volunteer.model;

public class Rudder {
    private Integer rudderId;

    private String school;

    private String organization;

    private String applicant;

    private Integer telNo;

    private String status;

    public Integer getRudderId() {
        return rudderId;
    }

    public void setRudderId(Integer rudderId) {
        this.rudderId = rudderId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public Integer getTelNo() {
        return telNo;
    }

    public void setTelNo(Integer telNo) {
        this.telNo = telNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}