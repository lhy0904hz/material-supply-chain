package com.material.chain.common.enums;

/**
 * 是否指定审批人
 */
public enum IsAssignEnum {
    YES(1, "指定审批人"),
    NO(2, "不指定审批人"),
    ;


    private Integer code;

    private String value;

    IsAssignEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
