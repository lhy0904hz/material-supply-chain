package com.material.chain.base.redis;

import com.material.chain.base.holder.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Component
public class RedissonLockManager {

    private static volatile RedissonLockManager instance;

    private static RedissonClient redissonClient;

/*    @Autowired
    private RedissonClient redissonClient;*/

    private RedissonLockManager() {}

    public static RedissonLockManager getInstance() {
        if (instance == null) {
            synchronized (RedissonLockManager.class) {
                if (instance == null) {
                    instance = new RedissonLockManager();
                }
            }
        }
        return instance;
    }

    static {
        log.info("RedissonLockManager.static initializer!");
        RedissonLockManager.redissonClient = SpringContextHolder.getBean(RedissonClient.class);
    }

    /**
     * 获取公平或非公平锁
     * @param key key
     * @param fair 是否获取公平锁
     * @return RLock
     */
    private RLock getLock(String key, boolean fair) {
        RLock lock;
        if (fair) {
            // 获取公平锁
            lock = redissonClient.getFairLock(key);
        } else {
            // 获取普通锁
            lock = redissonClient.getLock(key);
        }
        return lock;
    }

    /**
     * 加锁后执行自定义方法，无返回值
     * @param key key
     * @param timeoutSecond 超时时间
     * @param expireSecond 过期时间
     * @param method 执行业务逻辑
     */
    public void getLockToVoid(String key, Long timeoutSecond, Long expireSecond, Supplier<Void> method) {
        RLock lock = redissonClient.getLock(key);
        try {
            if (lock.tryLock(timeoutSecond, expireSecond, TimeUnit.SECONDS)) {
                method.get();
            }
        }catch (Exception e) {
            log.error("加锁失败", e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 加锁后执行自定义方法，有返回值
     * @param key key
     * @param timeoutSecond 超时时间
     * @param expireSecond 过期时间
     * @param method 执行业务逻辑
     */
    public <T> T getLockToCallable(String key, Long timeoutSecond, Long expireSecond, Supplier<T> method) {
        RLock lock = redissonClient.getLock(key);
        try {
            if (lock.tryLock(timeoutSecond, expireSecond, TimeUnit.SECONDS)) {
                return method.get();
            }
        }catch (Exception e) {
            log.error("加锁失败", e);
        } finally {
            lock.unlock();
        }
        return null;
    }
}
