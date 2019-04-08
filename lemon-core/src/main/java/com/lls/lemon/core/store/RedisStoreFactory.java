package com.lls.lemon.core.store;

import com.lls.lemon.core.redis.RedisClient;
import com.lls.lemon.core.serializer.Serializer;

/************************************
 * RedisStoreFactory
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class RedisStoreFactory implements StoreFactory {

    private RedisClient redisClient;
    private Serializer serializer;
    private Class targetClass;

    public RedisStoreFactory(RedisClient redisClient, Serializer serializer, Class targetClass) {
        this.redisClient = redisClient;
        this.serializer = serializer;
        this.targetClass = targetClass;
    }

    @Override
    public Store createStore() {
        return new RedisStore(redisClient, serializer, targetClass);
    }

}
