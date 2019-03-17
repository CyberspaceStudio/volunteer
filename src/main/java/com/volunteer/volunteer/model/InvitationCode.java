package com.volunteer.volunteer.model;

public class InvitationCode {
    private Integer codeId;

    private String codeContain;

    private Integer codePosition;

    private String codeSchool;

    private String organziation;

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public String getCodeContain() {
        return codeContain;
    }

    public void setCodeContain(String codeContain) {
        this.codeContain = codeContain == null ? null : codeContain.trim();
    }

    public Integer getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(Integer codePosition) {
        this.codePosition = codePosition;
    }

    public String getCodeSchool() {
        return codeSchool;
    }

    public void setCodeSchool(String codeSchool) {
        this.codeSchool = codeSchool == null ? null : codeSchool.trim();
    }

    public String getOrganziation() {
        return organziation;
    }

    public void setOrganziation(String organziation) {
        this.organziation = organziation == null ? null : organziation.trim();
    }
}