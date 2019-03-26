package com.lls.lemon.core.store;

import com.lls.lemon.core.serializer.Serializer;
import redis.clients.jedis.ShardedJedisPool;

/************************************
 * RedisStoreFactory
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class RedisStoreFactory implements StoreFactory {

    private ShardedJedisPool shardedJedisPool;
    private Serializer serializer;
    private Class targetClass;

    public RedisStoreFactory(ShardedJedisPool shardedJedisPool, Serializer serializer, Class targetClass) {
        this.shardedJedisPool = shardedJedisPool;
        this.serializer = serializer;
        this.targetClass = targetClass;
    }

    @Override
    public Store createStore() {
        return new RedisStore(shardedJedisPool, serializer, targetClass);
    }

}
