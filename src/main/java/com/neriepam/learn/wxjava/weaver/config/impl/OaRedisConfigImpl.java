package com.neriepam.learn.wxjava.weaver.config.impl;

import com.neriepam.learn.wxjava.weaver.config.OaConfigStorage;
import com.neriepam.learn.wxjava.weaver.constant.API;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于redis做保存
 */
public class OaRedisConfigImpl implements OaConfigStorage {
    private static final String TOKEN_KEY = "";
    private static final String TOKEN_EXPIRES_TIME_KEY = "";

    private Lock tokenLock = new ReentrantLock();

    private final RedisTemplate<String, Object> redisTemplate;

    private volatile String baseApiUrl;
    private volatile String appId;
    private volatile String secret;

    public OaRedisConfigImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setBaseApiUrl(String url) {
        baseApiUrl = url;
    }

    @Override
    public String getApiUrl(String path) {
        if (baseApiUrl == null) {
            baseApiUrl = API.DEFAULT_OA_BASE_URL;
        }
        return baseApiUrl + path;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


    @Override
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getToken() {
        return (String) redisTemplate.boundValueOps(TOKEN_KEY).get();
    }

    /**
     * 应该实现一个分布式锁，防止其他进程并发。
     */
    @Override
    public Lock getTokenLock() {
        return tokenLock;
    }

    @Override
    public boolean isTokenExpired() {
        String expiresTimeStr = (String) redisTemplate.boundValueOps(TOKEN_EXPIRES_TIME_KEY).get();
        if (expiresTimeStr != null) {
            return System.currentTimeMillis() > Long.parseLong(expiresTimeStr);
        }
        return true;
    }

    @Override
    public void expireToken() {
        redisTemplate.boundValueOps(TOKEN_EXPIRES_TIME_KEY).set("0");
    }

    @Override
    public void updateToken(String token, int expiresIn) {
        redisTemplate.boundValueOps(TOKEN_KEY).set(token);
        redisTemplate.boundValueOps(TOKEN_EXPIRES_TIME_KEY).set(
                (System.currentTimeMillis() + (expiresIn - 200) * 1000L) + ""
        );
    }
}
