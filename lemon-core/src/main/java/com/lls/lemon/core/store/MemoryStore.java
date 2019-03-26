package com.lls.lemon.core.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/************************************
 * MemoryStore
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class MemoryStore implements Store {

    private final Map<String, Object> store = new ConcurrentHashMap<>();

    @Override
    public void set(String key, Object val) {
        store.put(key, val);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        return (T) store.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return store.containsKey(key);
    }

    @Override
    public void delete(String key) {
        store.remove(key);
    }

}
