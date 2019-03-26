package com.lls.lemon.core.store;

import com.lls.lemon.core.exception.LemonArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/************************************
 * MemoryStore
 * @author liliangshan
 * @date 2019-03-26
 ************************************/
public class MemoryStore implements Store {

    private static final Logger logger = LoggerFactory.getLogger(MemoryStore.class);

    private static final String MEMORY_EXPIRED_CHECK = "MEMORY_EXPIRED_CHECK_";
    private static AtomicInteger idx = new AtomicInteger(0);
    private static AtomicBoolean runningService = new AtomicBoolean(false);

    private static Map<String, Object> store = new ConcurrentHashMap<>();
    private static Map<String, Long> expiredTimeMills = new ConcurrentHashMap<>();

    private static ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(MEMORY_EXPIRED_CHECK + idx.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    }, new ThreadPoolExecutor.DiscardPolicy());


    MemoryStore() {
        startService();
    }

    private static void startService() {
        if (runningService.get()) {
            logger.info(MEMORY_EXPIRED_CHECK + " service is running.");
            return;
        }
        if (runningService.compareAndSet(false, true)) {
            service.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    long currentTimeMills = System.currentTimeMillis();
                    List<String> keys = new ArrayList<>();
                    expiredTimeMills.forEach(new BiConsumer<String, Long>() {
                        @Override
                        public void accept(String key, Long value) {
                            if (value >= currentTimeMills) {
                                keys.add(key);
                            }
                        }
                    });
                    keys.forEach(new Consumer<String>() {
                        @Override
                        public void accept(String key) {
                            expiredTimeMills.remove(key);
                            store.remove(key);
                        }
                    });
                }
            }, 0, 1, TimeUnit.SECONDS);

            logger.info(MEMORY_EXPIRED_CHECK + " service is submitted.");
        }
    }

    @Override
    public void set(String key, Object val) {
        store.put(key, val);

    }

    @Override
    public void set(String key, Object val, long timeMills) {
        if (timeMills <= 0) {
            throw new LemonArgumentException("memory store expired time mills must be gt 0");
        }
        store.put(key, val);
        expiredTimeMills.put(key, timeMills + System.currentTimeMillis());
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
