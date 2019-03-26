package com.lls.lemon.core.store;

import com.lls.lemon.core.consts.LemonConsts;
import com.lls.lemon.core.exception.LemonArgumentException;
import com.lls.lemon.core.exception.LemonStoreException;
import com.lls.lemon.core.serializer.Serializer;
import com.lls.lemon.core.util.LemonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/************************************
 * RedisStore
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class RedisStore implements Store {

    private static final Logger logger = LoggerFactory.getLogger(RedisStore.class);

    private ShardedJedisPool shardedJedisPool;
    private Serializer serializer;
    private Class<?> targetClass;

    RedisStore(ShardedJedisPool shardedJedisPool, Serializer serializer, Class<?> targetClass) {
        this.shardedJedisPool = shardedJedisPool;
        this.serializer = serializer;
        this.targetClass = targetClass;
    }

    @Override
    public void set(String key, Object val) {
        String storeKey = this.buildStoreKey(key);
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        shardedJedis.set(storeKey.getBytes(), serializer.serialize(val));
    }

    @Override
    public void set(String key, Object val, long timeMills) {
        if (timeMills <= 0) {
            throw new LemonArgumentException("redis store expired time mills must be gt 0");
        }
        String storeKey = this.buildStoreKey(key);
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.psetex(storeKey.getBytes(), timeMills, serializer.serialize(val));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LemonStoreException(e.getMessage(), e);
        } finally {
            LemonUtils.close(shardedJedis);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        String storeKey = this.buildStoreKey(key);
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            byte[] bytes = shardedJedis.get(storeKey.getBytes());
            return (T) serializer.deserialize(bytes, this.targetClass);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LemonStoreException(e.getMessage(), e);
        } finally {
            LemonUtils.close(shardedJedis);
        }
    }

    @Override
    public boolean containsKey(String key) {
        String storeKey = this.buildStoreKey(key);
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.exists(storeKey.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LemonStoreException(e.getMessage(), e);
        } finally {
            LemonUtils.close(shardedJedis);
        }
    }

    @Override
    public void delete(String key) {
        String storeKey = this.buildStoreKey(key);
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.del(storeKey.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LemonStoreException(e.getMessage(), e);
        } finally {
            LemonUtils.close(shardedJedis);
        }
    }

    private String buildStoreKey(String sessionId) {
        return LemonConsts.LEMON_SESSION_ID.concat(":").concat(sessionId);
    }


}
