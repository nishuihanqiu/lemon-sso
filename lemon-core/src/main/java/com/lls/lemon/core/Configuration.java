package com.lls.lemon.core;

import com.lls.lemon.core.redis.RedisConfig;

/************************************
 * Configuration
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public class Configuration {

    private RedisConfig redisConfig;
    private String serializerVersion;
    private String storeCategory;

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

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory;
    }

    public String getStoreCategory() {
        return storeCategory;
    }

}
