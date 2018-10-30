package com.database.demo.entity;

/**
 * @author: 王轩
 * @description
 * @date: 2018/10/30
 */
public enum DataType {
    /**
     * 本地流量
     */
    LOCAL_DATA("LOCAL_DATA"),

    /**
     * 国内流量
     */
    DOMESTIC_DATA("DOMESTIC_DATA");

    private final String str;

    DataType(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
