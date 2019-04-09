package com.lls.lemon.core;

import com.lls.lemon.core.enums.LemonSerializerVersion;
import com.lls.lemon.core.enums.LemonStoreCategory;
import com.lls.lemon.core.model.LemonAuth;
import com.lls.lemon.core.redis.RedisClient;
import com.lls.lemon.core.redis.RedisConfig;
import com.lls.lemon.core.serializer.JacksonSerializer;
import com.lls.lemon.core.serializer.JdkSerializer;
import com.lls.lemon.core.serializer.ProtoStuffSerializer;
import com.lls.lemon.core.serializer.Serializer;
import com.lls.lemon.core.store.StoreManager;

/************************************
 * DefaultLemonContext
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public class DefaultLemonContext implements LemonContext {

    private final Configuration configuration;
    private RedisClient redisClient;
    private Serializer serializer;
    private Class<?> targetClass;
    private LemonStoreCategory lemonStoreCategory;

    public DefaultLemonContext(Configuration configuration) {
        this.configuration = configuration;
        if (configuration.getRedisConfig() != null) {
            this.redisClient = createRedisClient(configuration.getRedisConfig());
        }
        this.serializer = this.createSerializer(configuration.getSerializerVersion());
        this.lemonStoreCategory = LemonStoreCategory.getInstance(configuration.getStoreCategory());
        this.targetClass = LemonAuth.class;
        this.initializeStoreManager(this);
    }

    @Override
    public RedisClient getRedisClient() {
        return redisClient;
    }

    @Override
    public Serializer getSerializer() {
        return serializer;
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public LemonStoreCategory getLemonStoreCategory() {
        return lemonStoreCategory;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    private RedisClient createRedisClient(RedisConfig redisConfig) {
        return new RedisClient(redisConfig);
    }

    private Serializer createSerializer(String serializerVersion) {
        if (LemonSerializerVersion.JACKSON_SERIALIZER.getCode().equals(serializerVersion)) {
            return new JacksonSerializer();
        }
        if (LemonSerializerVersion.PROTO_STUFF_SERIALIZER.getCode().equals(serializerVersion)) {
            return new ProtoStuffSerializer();
        }

        return new JdkSerializer();
    }

    private void initializeStoreManager(LemonContext context) {
        StoreManager.getInstance().registryContext(context);
    }


}
