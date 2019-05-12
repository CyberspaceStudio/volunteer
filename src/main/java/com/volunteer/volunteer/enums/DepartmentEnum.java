package com.volunteer.volunteer.enums;

public enum DepartmentEnum {
    GZS("工作室"),HBB("环保部"),HSZH("红十字会"),JLB("交流部"),PXB("培训部"),
    ZJB("支教部"),XCB("宣传部"),XMB("项目部"),HDB("活动部"),MSC("秘书处"),JCC("决策层");

    private String department;

    private DepartmentEnum(String department) {
        this.department = department;
    }

    public static String getDepartment(int codeNumber){
        String res ="";
        switch (codeNumber){
            case 1:
                res = GZS.getDepartment();
                break;
            case 2:
                res = HBB.getDepartment();
                break;
            case 3:
                res = HSZH.getDepartment();
                break;
            case 4:
                res = JLB.getDepartment();
                break;
            case 5:
                res = PXB.getDepartment();
                break;
            case 6:
                res = ZJB.getDepartment();
                break;
            case 7:
                res = XCB.getDepartment();
                break;
            case 8:
                res = XMB.getDepartment();
                break;
            case 9:
                res = HDB.getDepartment();
                break;
            case 10:
                res = MSC.getDepartment();
                break;
            case 11:
                res = JCC.getDepartment();
                break;
                default:
                    res = "error";
        }
        return res;
    }
    private String getDepartment(){
        return department;
    }
}
