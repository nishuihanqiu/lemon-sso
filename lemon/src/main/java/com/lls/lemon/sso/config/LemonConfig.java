package com.lls.lemon.sso.config;

import com.lls.lemon.core.LemonConfiguration;
import com.lls.lemon.core.LemonContext;
import com.lls.lemon.core.LemonContextFactory;
import com.lls.lemon.core.enums.LemonSerializerVersion;
import com.lls.lemon.core.enums.LemonStoreCategory;
import com.lls.lemon.core.redis.RedisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/************************************
 * LemonConfig
 * @author liliangshan
 * @date 2019-04-09
 ************************************/
@Configuration
public class LemonConfig {

    @Bean
    public LemonContext registryContext(LemonConfiguration configuration) {
        LemonContextFactory factory = new LemonContextFactory(configuration);
        return factory.makeObject();
    }

    @Bean
    public LemonConfiguration getConfiguration() {
        LemonConfiguration configuration =  new LemonConfiguration();
        RedisConfig config = new RedisConfig();
        config.setAddresses("localhost:6370");
        configuration.setRedisConfig(config);
        configuration.setSerializerVersion(LemonSerializerVersion.JDK_SERIALIZER.getCode());
        configuration.setStoreCategoryCode(LemonStoreCategory.REDIS_STORE.getCode());
        return configuration;
    }

}
