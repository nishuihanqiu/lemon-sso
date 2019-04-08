package com.lls.lemon.core;

import com.lls.lemon.core.enums.LemonStoreCategory;
import com.lls.lemon.core.redis.RedisClient;
import com.lls.lemon.core.serializer.Serializer;

/************************************
 * LemonContext
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public interface LemonContext {

    RedisClient getRedisClient();

    Serializer getSerializer();

    Class<?> getTargetClass();

    LemonStoreCategory getLemonStoreCategory();

    Configuration getConfiguration();


}
