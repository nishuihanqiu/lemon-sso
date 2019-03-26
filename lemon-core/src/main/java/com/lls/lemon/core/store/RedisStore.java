package com.lls.lemon.core.store;

import com.lls.lemon.core.serializer.Serializer;
import redis.clients.jedis.ShardedJedis;

/************************************
 * RedisStore
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class RedisStore implements Store {

    private ShardedJedis client;
    private Serializer serializer;

    RedisStore(ShardedJedis client, Serializer serializer) {
        this.client = client;
        this.serializer = serializer;
    }

    @Override
    public void set(String key, Object val) {

    }

    @Override
    public void set(String key, Object val, long timeMills) {

    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public boolean containsKey(String key) {
        return false;
    }

    @Override
    public void delete(String key) {

    }


}
