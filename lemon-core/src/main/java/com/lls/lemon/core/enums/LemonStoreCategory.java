package com.lls.lemon.core.enums;

/************************************
 * LemonStoreCategory
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public enum LemonStoreCategory {

  MEMORY_STORE, REDIS_STORE;

  public static LemonStoreCategory getInstance(String storeCategory) {
    if (REDIS_STORE.name().equals(storeCategory)) {
      return REDIS_STORE;
    }
    return MEMORY_STORE;
  }

}
