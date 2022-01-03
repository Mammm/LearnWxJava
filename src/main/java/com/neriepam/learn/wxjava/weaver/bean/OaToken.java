package com.neriepam.learn.wxjava.weaver.bean;

import com.neriepam.learn.wxjava.weaver.util.JsonUtil;
import lombok.Data;

@Data
public class OaToken {
    private static final long serialVersionUID = -22005L;

    private String token;

    public static OaToken fromJson(String json) {
        return JsonUtil.readValue(json, OaToken.class);
    }
}
