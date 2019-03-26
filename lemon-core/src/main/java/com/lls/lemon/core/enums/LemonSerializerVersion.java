package com.lls.lemon.core.enums;

/************************************
 * LemonSerializerVersion
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public enum LemonSerializerVersion {

    JDK_SERIALIZER(1, "jdk_serializer"),
    JACKSON_SERIALIZER(2, "jackson_serializer"),
    PROTO_STUFF_SERIALIZER(3, "proto_stuff_serializer");

    private int category;
    private String code;

    LemonSerializerVersion(int category, String code) {
        this.category = category;
        this.code = code;
    }

    public int getCategory() {
        return category;
    }

    public String getCode() {
        return code;
    }

}
