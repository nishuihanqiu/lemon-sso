package com.lls.lemon.core.model;

import java.io.Serializable;
import java.util.Map;

/************************************
 * LemonAuth
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class LemonAuth implements Serializable {

    private static final long serialVersionUID = -2419843212L;

    private Long authId;
    private String authName;
    private Map<String, String> arguments;
    private String version;
    private long expiredTimeMills;
    private long expiredFlushTimeMills;


    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getExpiredTimeMills() {
        return expiredTimeMills;
    }

    public void setExpiredTimeMills(long expiredTimeMills) {
        this.expiredTimeMills = expiredTimeMills;
    }

    public long getExpiredFlushTimeMills() {
        return expiredFlushTimeMills;
    }

    public void setExpiredFlushTimeMills(long expiredFlushTimeMills) {
        this.expiredFlushTimeMills = expiredFlushTimeMills;
    }
}
