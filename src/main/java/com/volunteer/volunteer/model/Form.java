package com.volunteer.volunteer.model;

import java.util.Date;

public class Form {
    private Integer formTableId;

    private String formId;

    private Integer wechatId;

    private Date deadline;

    public Integer getFormTableId() {
        return formTableId;
    }

    public void setFormTableId(Integer formTableId) {
        this.formTableId = formTableId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public Integer getWechatId() {
        return wechatId;
    }

    public void setWechatId(Integer wechatId) {
        this.wechatId = wechatId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}