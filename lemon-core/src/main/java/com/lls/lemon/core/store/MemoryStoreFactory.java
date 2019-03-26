package com.lls.lemon.core.store;

/************************************
 * MemoryStoreFactory
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class MemoryStoreFactory implements StoreFactory {

    @Override
    public Store createStore() {
        return new MemoryStore();
    }

}
