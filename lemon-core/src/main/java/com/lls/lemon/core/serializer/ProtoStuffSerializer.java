package com.lls.lemon.core.serializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.lls.lemon.core.enums.LemonSerializerVersion;

/************************************
 * ProtoStuffSerializer
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class ProtoStuffSerializer implements Serializer {

    private static <T> RuntimeSchema<T> getSchema(Class<T> clz) {
        return (RuntimeSchema<T>) RuntimeSchema.getSchema(clz); // protostuff 内部已做缓存考虑
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> byte[] serialize(T object) {
        // 获取泛型对象的类型
        Class<T> clz = (Class<T>) object.getClass();
        // 创建泛型对象的schema对象
        RuntimeSchema<T> schema = getSchema(clz);
        // 创建LinkedBuffer对象
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        return ProtobufIOUtil.toByteArray(object, schema, buffer);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) {
        // 创建泛型对象的schema对象
        RuntimeSchema<T> schema = getSchema(clz);
        // 根据schema实例化对象
        T message = schema.newMessage(); // 这里不要用对象自己的 newInstance 会有坑 用工具提供的message
        // 将字节数组中的数据反序列化到message对象
        ProtobufIOUtil.mergeFrom(bytes, message, schema);
        return message;
    }

    @Override
    public LemonSerializerVersion getSerializerVersion() {
        return LemonSerializerVersion.PROTO_STUFF_SERIALIZER;
    }


}
