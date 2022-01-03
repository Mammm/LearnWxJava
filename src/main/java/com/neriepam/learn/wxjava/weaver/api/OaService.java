package com.neriepam.learn.wxjava.weaver.api;

import com.neriepam.learn.wxjava.weaver.config.OaConfigStorage;
import com.neriepam.learn.wxjava.weaver.error.OaErrorException;

/**
 * 泛微api的服务
 */
public interface OaService {

    /**
     * 获取oa接口权限token
     *
     * @return token
     * @throws OaErrorException exception
     */
    String getToken() throws OaErrorException;

    /**
     * 注入配置接口的实现
     *
     * @param oaConfigStorage {@link OaConfigStorage}
     */
    void setOaConfigStorage(OaConfigStorage oaConfigStorage);

    /**
     * 获得配置对象
     *
     * @return configStorage
     */
    OaConfigStorage getOaConfigStorage();

    /**
     * 初始化http请求
     */
    void initHttp();
}
