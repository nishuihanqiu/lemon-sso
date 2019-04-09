package com.lls.lemon.core.enums;

/************************************
 * LemonStoreCategory
 * @author liliangshan
 * @date 2019-04-08
 ************************************/
public enum LemonStoreCategory {

  MEMORY_STORE("MEMORY_STORE"),
  REDIS_STORE("REDIS_STORE");

  private String code;

  LemonStoreCategory(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public static LemonStoreCategory getByCode(String categoryCode) {
    if (REDIS_STORE.getCode().equals(categoryCode)) {
      return REDIS_STORE;
    }
    return MEMORY_STORE;
  }

}
