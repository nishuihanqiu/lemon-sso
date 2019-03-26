package com.lls.lemon.core.serializer;

import com.lls.lemon.core.enums.LemonSerializerVersion;

/************************************
 * Serializer
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public interface Serializer {

    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] bytes, Class<T> clz);

    LemonSerializerVersion getSerializerVersion();

}
