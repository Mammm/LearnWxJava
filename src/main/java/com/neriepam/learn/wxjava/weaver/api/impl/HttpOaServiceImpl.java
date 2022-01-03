package com.neriepam.learn.wxjava.weaver.api.impl;

import com.neriepam.learn.wxjava.weaver.bean.OaToken;
import com.neriepam.learn.wxjava.weaver.config.OaConfigStorage;
import com.neriepam.learn.wxjava.weaver.constant.API;
import com.neriepam.learn.wxjava.weaver.constant.REQ;
import com.neriepam.learn.wxjava.weaver.error.OaError;
import com.neriepam.learn.wxjava.weaver.error.OaErrorException;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.locks.Lock;

public class HttpOaServiceImpl extends BaseOaServiceImpl {
    private RestTemplate restTemplate;

    @Override
    public String getToken() throws OaErrorException {
        final OaConfigStorage configStorage = getOaConfigStorage();
        if (!configStorage.isTokenExpired()) {
            return configStorage.getToken();
        }
        Lock lock = configStorage.getTokenLock();
        lock.lock();
        try {
            //double check
            if (!configStorage.isTokenExpired()) {
                return configStorage.getToken();
            }
            String url = configStorage.getApiUrl(API.GET_TOKEN);
            MediaType mediaType = MediaType.parseMediaType(REQ.CONTENT_TYPE_VALUE);
            RequestEntity<Void> reqEntity = RequestEntity.post(url)
                    .header(REQ.Header.APP_ID, configStorage.getAppId())
                    .header(REQ.Header.SECRET, configStorage.getSecret())
                    .contentType(mediaType)
                    .build();
            ResponseEntity<String> respEntity = restTemplate.exchange(reqEntity, String.class);
            if (!StringUtils.hasText(respEntity.getBody())) {
                throw OaErrorException.emptyBody();
            }
            OaError error = OaError.fromJson(respEntity.getBody());
            if (!error.getStatus()) {
                throw new OaErrorException(error);
            }
            OaToken token = OaToken.fromJson(respEntity.getBody());
            configStorage.updateToken(token.getToken(), 3600);
        } finally {
            lock.unlock();
        }
        return configStorage.getToken();
    }

    @Override
    public void initHttp() {
        restTemplate = new RestTemplate();
    }
}
