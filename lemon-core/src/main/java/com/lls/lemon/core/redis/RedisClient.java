package com.lls.lemon.core.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/************************************
 * RedisClient
 * @author liliangshan
 * @date 2019-04-07
 ************************************/
public class RedisClient {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private RedisConfig redisConfig;

    private ShardedJedisPool shardedJedisPool;

    private static ReentrantLock LOCK = new ReentrantLock();

    public RedisClient(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public ShardedJedis getShardedJedis() {
        if (shardedJedisPool == null) {
            LOCK.lock();
            try {
                shardedJedisPool = createShardedJedisPool();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                LOCK.unlock();
            }
        }

        if (shardedJedisPool == null) {
            throw new NullPointerException("redis client shared pool is null.");
        }

        return shardedJedisPool.getResource();
    }

    private ShardedJedisPool createShardedJedisPool() {
        JedisPoolConfig config = redisConfig.buildJedisPoolConfig();
        List<JedisShardInfo> jedisShardInfoList = this.getShardInfoList();
        return new ShardedJedisPool(config, jedisShardInfoList);
    }

    private List<JedisShardInfo> getShardInfoList() {
        List<JedisShardInfo> jedisShardInfoList = new LinkedList<>();
        List<String> addresses = redisConfig.getAddressList();
        JedisShardInfo jedisShardInfo;
        for (String address : addresses) {
            jedisShardInfo = new JedisShardInfo(address);
            jedisShardInfoList.add(jedisShardInfo);
        }
        return jedisShardInfoList;
    }

    public void close() {
        logger.info("redis client is closing.");
        if (shardedJedisPool != null) {
            shardedJedisPool.close();
        }
        logger.info("redis client closed.");
    }

}
