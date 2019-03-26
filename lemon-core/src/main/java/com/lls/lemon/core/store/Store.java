package com.lls.lemon.core.store;

/************************************
 * Store
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public interface Store {

    void set(String key, Object val);

    <T> T get(String key);

    boolean containsKey(String key);

    void delete(String key);

}
