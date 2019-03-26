package com.lls.lemon.core.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lls.lemon.core.enums.LemonSerializerVersion;
import com.lls.lemon.core.exception.LemonSerializerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/************************************
 * JacksonSerializer
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class JacksonSerializer implements Serializer {

    private static final Logger logger = LoggerFactory.getLogger(JacksonSerializer.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
    }

    @Override
    public <T> byte[] serialize(T object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new LemonSerializerException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) {
        try {
            return objectMapper.readValue(bytes, clz);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            throw new LemonSerializerException(e.getMessage(), e);
        }
    }

    @Override
    public LemonSerializerVersion getSerializerVersion() {
        return LemonSerializerVersion.JACKSON_SERIALIZER;
    }
}
