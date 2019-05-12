package com.volunteer.volunteer.enums;

public enum OrganizationEnum {
    XD("西电青年志愿者总队");
    private String organization;

    private OrganizationEnum(String organization) {
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }
}
