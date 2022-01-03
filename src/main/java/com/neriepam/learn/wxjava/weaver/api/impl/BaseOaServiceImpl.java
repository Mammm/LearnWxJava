package com.neriepam.learn.wxjava.weaver.api.impl;

import com.neriepam.learn.wxjava.weaver.api.OaService;
import com.neriepam.learn.wxjava.weaver.config.OaConfigStorage;

public abstract class BaseOaServiceImpl implements OaService {

    private OaConfigStorage configStorage;

    @Override
    public void setOaConfigStorage(OaConfigStorage oaConfigStorage) {
        configStorage = oaConfigStorage;
        initHttp();
    }

    @Override
    public OaConfigStorage getOaConfigStorage() {
        return configStorage;
    }
}
