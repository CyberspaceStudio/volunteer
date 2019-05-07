package com.volunteer.volunteer.model;

public class EnrollPerson {
    private Integer mainId;

    private String realName;

    /**字符串："男","女",数据库有相应操作*/
    private String sex;

    private String telNo;

    private String wechat;

    private String school;

    private String organization;

    private String introduction;

    private String firstChoice;

    private String firstInterviewScore;

    private String firstInterviewImpression;

    private String secondChoice;

    private String secondInterviewScore;

    private String secondInterviewImpression;

    private String thirdChoice;

    private String thirdInterviewScore;

    private String thirdInterviewImpression;

    private String finalDepartment;

    /**
    *   0   未面试             501 正在一面                 502 正在二面
    *   503 通过一面，待二面    504 通过一面，未通过二面       505 成为部员
    */
    private String enrollStatus;

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice == null ? null : firstChoice.trim();
    }

    public String getFirstInterviewScore() {
        return firstInterviewScore;
    }

    public void setFirstInterviewScore(String firstInterviewScore) {
        this.firstInterviewScore = firstInterviewScore == null ? null : firstInterviewScore.trim();
    }

    public String getFirstInterviewImpression() {
        return firstInterviewImpression;
    }

    public void setFirstInterviewImpression(String firstInterviewImpression) {
        this.firstInterviewImpression = firstInterviewImpression == null ? null : firstInterviewImpression.trim();
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice == null ? null : secondChoice.trim();
    }

    public String getSecondInterviewScore() {
        return secondInterviewScore;
    }

    public void setSecondInterviewScore(String secondInterviewScore) {
        this.secondInterviewScore = secondInterviewScore == null ? null : secondInterviewScore.trim();
    }

    public String getSecondInterviewImpression() {
        return secondInterviewImpression;
    }

    public void setSecondInterviewImpression(String secondInterviewImpression) {
        this.secondInterviewImpression = secondInterviewImpression == null ? null : secondInterviewImpression.trim();
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public void setThirdChoice(String thirdChoice) {
        this.thirdChoice = thirdChoice == null ? null : thirdChoice.trim();
    }

    public String getThirdInterviewScore() {
        return thirdInterviewScore;
    }

    public void setThirdInterviewScore(String thirdInterviewScore) {
        this.thirdInterviewScore = thirdInterviewScore == null ? null : thirdInterviewScore.trim();
    }

    public String getThirdInterviewImpression() {
        return thirdInterviewImpression;
    }

    public void setThirdInterviewImpression(String thirdInterviewImpression) {
        this.thirdInterviewImpression = thirdInterviewImpression == null ? null : thirdInterviewImpression.trim();
    }

    public String getFinalDepartment() {
        return finalDepartment;
    }

    public void setFinalDepartment(String finalDepartment) {
        this.finalDepartment = finalDepartment == null ? null : finalDepartment.trim();
    }

    public String getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(String enrollStatus) {
        this.enrollStatus = enrollStatus == null ? null : enrollStatus.trim();
    }
}