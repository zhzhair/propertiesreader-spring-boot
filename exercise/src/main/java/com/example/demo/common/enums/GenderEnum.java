package com.example.demo.common.enums;

public enum GenderEnum {
    FEMALE("2","女"),
    MALE("1","男"),
    UNKNOWN("0","未知");

    private String code;
    private String description;

    public static GenderEnum fromStatus(String code) {
        GenderEnum[] genderEnums = GenderEnum.values();
        for (GenderEnum genderEnum : genderEnums) {
            if (genderEnum.code.equals(code)) {
                return genderEnum;
            }
        }
        return null;
    }

    GenderEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
