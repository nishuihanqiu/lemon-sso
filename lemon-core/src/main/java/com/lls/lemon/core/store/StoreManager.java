package com.lls.lemon.core.store;

import com.lls.lemon.core.LemonContext;
import com.lls.lemon.core.exception.LemonException;

/************************************
 * StoreManager
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class StoreManager {

    private Store store;
    private LemonContext context;

    private StoreManager() {
    }

    public static StoreManager getInstance() {
        return Holder.INSTANCE;
    }

    public Store getStore() {
        if (store != null) {
            return store;
        }
        if (context == null) {
            store = createMemoryStore();
        }
        if (store != null) {
            return store;
        }
        throw new LemonException("In order to build redis store, lemon context must not be null.");
    }

    private Store createMemoryStore() {
        MemoryStoreFactory factory = new MemoryStoreFactory();
        return factory.createStore();
    }

    private Store createRedisStore() {
        if (context == null) {
            throw new LemonException("In order to build redis store, lemon context must not be null.");
        }
        RedisStoreFactory factory = new RedisStoreFactory(context.getRedisClient(), context.getSerializer(),
                context.getTargetClass());
        return factory.createStore();
    }

    private static class Holder {
        private static final StoreManager INSTANCE = new StoreManager();
    }

}
