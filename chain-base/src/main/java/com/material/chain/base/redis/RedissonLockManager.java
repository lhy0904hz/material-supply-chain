package com.material.chain.base.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Component
public class RedissonLockManager {

    @Autowired
    private RedissonClient redissonClient;

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
        boolean getLock = false;
        try {
            if (getLock == lock.tryLock(timeoutSecond, expireSecond, TimeUnit.SECONDS)) {
                method.get();
            } else {
                log.warn("获取锁超时");
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
        boolean getLock = false;
        try {
            if (getLock == lock.tryLock(timeoutSecond, expireSecond, TimeUnit.SECONDS)) {
                return method.get();
            } else {
                log.warn("获取锁超时");
            }
        }catch (Exception e) {
            log.error("加锁失败", e);
        } finally {
            lock.unlock();
        }
        return null;
    }
}
