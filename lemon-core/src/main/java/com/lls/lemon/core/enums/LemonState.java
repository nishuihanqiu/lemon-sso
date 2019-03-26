package com.lls.lemon.core.enums;

/************************************
 * LemonState
 * @author liliangshan
 * @date 2019-03-25
 ************************************/
public enum LemonState {

    SUCCESS(200, "lemon_success", "成功"),
    FAILED(400, "lemon_failed", "失败"),
    NET_ERROR(500, "lemon_network_error", "无响应");

    private int status;
    private String code;
    private String message;

    LemonState(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}

