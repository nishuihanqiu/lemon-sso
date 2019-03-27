package com.lls.lemon.core.store;

/************************************
 * StoreManager
 * @author liliangshan
 * @date 2019-03-27
 ************************************/
public class StoreManager {

    private Store store;

    private StoreManager() {
        // todo
        MemoryStoreFactory factory = new MemoryStoreFactory();
        store = factory.createStore();
    }

    public static StoreManager getInstance() {
        return Holder.INSTANCE;
    }

    public Store getStore() {
        return store;
    }

    private static class Holder {
        private static final StoreManager INSTANCE = new StoreManager();
    }

}
