package com.lls.lemon.core.serializer;

/************************************
 * Serializer
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public interface Serializer {

    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] bytes, Class<T> clz);

}
