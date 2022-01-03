package com.neriepam.learn.wxjava.weaver.config;

import java.util.concurrent.locks.Lock;

/**
 * oa配置储存领域接口
 */
public interface OaConfigStorage {

    /**
     * 设置接口域名
     *
     * @param baseApiUrl 域名
     */
    void setBaseApiUrl(String baseApiUrl);

    /**
     * 获得真实的api地址
     *
     * @param path api的uri
     * @return api url
     */
    String getApiUrl(String path);

    /**
     * 获取appId
     *
     * @return appId
     */
    String getAppId();

    /**
     * 获取secret
     *
     * @return secret
     */
    String getSecret();

    /**
     * 获取token
     *
     * @return token
     */
    String getToken();

    /**
     * 更新token时用，防止线程并发
     *
     * @return lock
     */
    Lock getTokenLock();

    /**
     * token是否过期？
     *
     * @return bool
     */
    boolean isTokenExpired();

    /**
     * 主动过期token
     */
    void expireToken();

    /**
     * 更新token
     *
     * @param token     token
     * @param expiresIn token有效期
     */
    void updateToken(String token, int expiresIn);
}
