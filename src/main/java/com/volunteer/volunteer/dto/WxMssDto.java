package com.volunteer.volunteer.dto;

import java.util.Map;

/**
 * @author ：Maolin
 * @className ：WxMssDto
 * @date ：Created in 2019/7/26 17:21
 * @description： Template message of wechat
 * @version: 1.0
 */
public class WxMssDto {
    private String touser;//用户openid
    private String templateId;//模版id
    private String page = "index";//默认跳到小程序首页
    private String formId;//收集到的用户formid
    private String emphasis_keyword = "西电青志面试通知";//放大那个推送字段
    private Map<String, TemplateData> data;//推送文字

    public WxMssDto() {
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }
}
