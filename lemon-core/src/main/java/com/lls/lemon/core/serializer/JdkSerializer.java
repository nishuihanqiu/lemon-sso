package com.lls.lemon.core.serializer;

import com.lls.lemon.core.exception.LemonSerializerException;
import com.lls.lemon.core.util.LemonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/************************************
 * JdkSerializer
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class JdkSerializer implements Serializer {

    private static final Logger logger = LoggerFactory.getLogger(JdkSerializer.class);

    @Override
    public <T> byte[] serialize(T object) {
        ObjectOutputStream outputStream = null;
        ByteArrayOutputStream arrayOutputStream = null;
        try {
            arrayOutputStream = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(arrayOutputStream);
            outputStream.writeObject(object);
            outputStream.flush();
            arrayOutputStream.flush();
            return arrayOutputStream.toByteArray();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LemonSerializerException(e.getMessage(), e);
        } finally {
            LemonUtils.close(outputStream);
            LemonUtils.close(arrayOutputStream);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) {
        ByteArrayInputStream arrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 反序列化
            arrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(arrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new LemonSerializerException(e.getMessage(), e);
        } finally {
            LemonUtils.close(arrayInputStream);
            LemonUtils.close(objectInputStream);
        }
    }


}
