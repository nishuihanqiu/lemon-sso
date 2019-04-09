package com.lls.lemon.core.store;

import com.lls.lemon.core.LemonContext;
import com.lls.lemon.core.enums.LemonStoreCategory;
import com.lls.lemon.core.exception.LemonException;

import java.util.concurrent.atomic.AtomicBoolean;

/************************************
 * StoreManager
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class StoreManager {

    private Store store;
    private AtomicBoolean registered = new AtomicBoolean(false);

    private StoreManager() {
    }

    public static StoreManager getInstance() {
        return Holder.INSTANCE;
    }

    public Store getStore() {
        if (store != null) {
            return store;
        }
        throw new LemonException("In order to get store, lemon context must be register store manager.");
    }

    public void registryContext(LemonContext context) {
        if (store != null) {
            return;
        }
        if (registered.compareAndSet(false, true)) {
            if (context == null || LemonStoreCategory.MEMORY_STORE == context.getLemonStoreCategory()) {
                store = this.createMemoryStore();
                return;
            }

            store = this.createRedisStore(context);
        }
    }

    private Store createMemoryStore() {
        MemoryStoreFactory factory = new MemoryStoreFactory();
        return factory.createStore();
    }

    private Store createRedisStore(LemonContext context) {
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
