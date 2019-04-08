package com.lls.lemon.core.redis;

import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/************************************
 * RedisConfig
 * @author liliangshan
 * @date 2019-04-07
 ************************************/
public class RedisConfig {

    private String addresses;
    private int maxTotal = 8;
    private int maxIdle = 8;
    private int minIdle = 0;
    private boolean lifo = true;
    private boolean fairness = false;
    private long maxWaitMillis = -1L;
    private long minEvictableIdleTimeMillis = 1800000L;
    private long evictShutdownTimeoutMillis = 10000L;
    private long softMinEvictableIdleTimeMillis = -1L;
    private int numTestsPerEvictionRun = 3;
    private boolean testOnCreate = false;
    private boolean testOnBorrow = false;
    private boolean testOnReturn = false;
    private boolean testWhileIdle = false;
    private long timeBetweenEvictionRunsMillis = -1L;
    private boolean blockWhenExhausted = true;
    private boolean jmxEnabled = true;
    private String jmxNamePrefix = "pool";
    private String jmxNameBase;

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddressList() {
        if (this.addresses == null || this.addresses.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(addresses.split(","));
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public boolean isLifo() {
        return lifo;
    }

    public void setLifo(boolean lifo) {
        this.lifo = lifo;
    }

    public boolean isFairness() {
        return fairness;
    }

    public void setFairness(boolean fairness) {
        this.fairness = fairness;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public long getEvictShutdownTimeoutMillis() {
        return evictShutdownTimeoutMillis;
    }

    public void setEvictShutdownTimeoutMillis(long evictShutdownTimeoutMillis) {
        this.evictShutdownTimeoutMillis = evictShutdownTimeoutMillis;
    }

    public long getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    public void setSoftMinEvictableIdleTimeMillis(long softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public boolean isTestOnCreate() {
        return testOnCreate;
    }

    public void setTestOnCreate(boolean testOnCreate) {
        this.testOnCreate = testOnCreate;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public boolean isBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public boolean isJmxEnabled() {
        return jmxEnabled;
    }

    public void setJmxEnabled(boolean jmxEnabled) {
        this.jmxEnabled = jmxEnabled;
    }

    public String getJmxNamePrefix() {
        return jmxNamePrefix;
    }

    public void setJmxNamePrefix(String jmxNamePrefix) {
        this.jmxNamePrefix = jmxNamePrefix;
    }

    public String getJmxNameBase() {
        return jmxNameBase;
    }

    public void setJmxNameBase(String jmxNameBase) {
        this.jmxNameBase = jmxNameBase;
    }

    public JedisPoolConfig buildJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(this.getMaxTotal());
        jedisPoolConfig.setMaxIdle(this.getMaxIdle());
        jedisPoolConfig.setMinIdle(this.getMinIdle());
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(this.getMaxWaitMillis());
        // 在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(this.isTestOnBorrow());
        // 调用returnObject方法时，是否进行有效检查
        jedisPoolConfig.setTestOnReturn(this.isTestOnReturn());
        // Idle时进行连接扫描
        jedisPoolConfig.setTestWhileIdle(this.isTestWhileIdle());
        // 表示idle object evitor两次扫描之间要sleep的毫秒数
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(this.getTimeBetweenEvictionRunsMillis());
        // 表示idle object evitor每次扫描的最多的对象数
        jedisPoolConfig.setNumTestsPerEvictionRun(this.getNumTestsPerEvictionRun());
        // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        jedisPoolConfig.setMinEvictableIdleTimeMillis(this.getMinEvictableIdleTimeMillis());
        return jedisPoolConfig;
    }
}
