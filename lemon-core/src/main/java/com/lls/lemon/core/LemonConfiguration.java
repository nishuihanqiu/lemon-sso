package com.lls.lemon.core;

import com.lls.lemon.core.redis.RedisConfig;

/************************************
 * LemonConfiguration
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public class LemonConfiguration {

    private RedisConfig redisConfig;
    private String serializerVersion;
    private String storeCategoryCode;

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public void setSerializerVersion(String serializerVersion) {
        this.serializerVersion = serializerVersion;
    }

    public String getSerializerVersion() {
        return serializerVersion;
    }

    public void setStoreCategoryCode(String storeCategoryCode) {
        this.storeCategoryCode = storeCategoryCode;
    }

    public String getStoreCategoryCode() {
        return storeCategoryCode;
    }

}
