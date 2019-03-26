package com.lls.lemon.core.store;

import com.lls.lemon.core.serializer.Serializer;
import redis.clients.jedis.ShardedJedis;

/************************************
 * RedisStoreFactory
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class RedisStoreFactory implements StoreFactory {

    private ShardedJedis shardedJedis;
    private Serializer serializer;

    public RedisStoreFactory(ShardedJedis shardedJedis, Serializer serializer) {
        this.shardedJedis = shardedJedis;
        this.serializer = serializer;
    }

    @Override
    public Store createStore() {
        return new RedisStore(shardedJedis, serializer);
    }

}
