package com.volunteer.volunteer.model;

public class NewMember {
    private Integer enrollId;

    private Integer wechatId;

    private String introduction;

    private String enrollName;

    private String firstChoice;

    private String secondChoice;

    private String thirdChoice;

    private String status;

    private String firstInterviewScore;

    private String firstInterviewImpression;

    private String secondInterviewOr;

    private String secondInterviewSign;

    public Integer getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Integer enrollId) {
        this.enrollId = enrollId;
    }

    public Integer getWechatId() {
        return wechatId;
    }

    public void setWechatId(Integer wechatId) {
        this.wechatId = wechatId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getEnrollName() {
        return enrollName;
    }

    public void setEnrollName(String enrollName) {
        this.enrollName = enrollName == null ? null : enrollName.trim();
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice == null ? null : firstChoice.trim();
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice == null ? null : secondChoice.trim();
    }

    public String getThirdChoice() {
        return thirdChoice;
    }

    public void setThirdChoice(String thirdChoice) {
        this.thirdChoice = thirdChoice == null ? null : thirdChoice.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getSecondInterviewOr() {
        return secondInterviewOr;
    }

    public void setSecondInterviewOr(String secondInterviewOr) {
        this.secondInterviewOr = secondInterviewOr == null ? null : secondInterviewOr.trim();
    }

    public String getSecondInterviewSign() {
        return secondInterviewSign;
    }

    public void setSecondInterviewSign(String secondInterviewSign) {
        this.secondInterviewSign = secondInterviewSign == null ? null : secondInterviewSign.trim();
    }
}