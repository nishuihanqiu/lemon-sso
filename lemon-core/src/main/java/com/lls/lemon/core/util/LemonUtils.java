package com.lls.lemon.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/************************************
 * LemonUtils
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class LemonUtils {

    private static final Logger logger = LoggerFactory.getLogger(LemonUtils.class);

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
