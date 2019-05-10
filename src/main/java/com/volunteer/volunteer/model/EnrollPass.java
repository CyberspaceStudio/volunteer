package com.volunteer.volunteer.model;


/**
 * @description: 记录志愿是否通过
 * @author: maolin
 * @create: 2019-05-06 20:30
 **/
public class EnrollPass {
    private Integer passId;

    private Integer mainId;


    /**
     *   firstPass secondPass thirdPass 数字代表
     *   0 未面试   1 已面试   100 面试通过   500 面试未通过
     */
    private Integer firstPass;

    private Integer secondPass;

    private Integer thirdPass;

    public Integer getPassId() {
        return passId;
    }

    public void setPassId(Integer passId) {
        this.passId = passId;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public Integer getFirstPass() {
        return firstPass;
    }

    public void setFirstPass(Integer firstPass) {
        this.firstPass = firstPass;
    }

    public Integer getSecondPass() {
        return secondPass;
    }

    public void setSecondPass(Integer secondPass) {
        this.secondPass = secondPass;
    }

    public Integer getThirdPass() {
        return thirdPass;
    }

    public void setThirdPass(Integer thirdPass) {
        this.thirdPass = thirdPass;
    }
}