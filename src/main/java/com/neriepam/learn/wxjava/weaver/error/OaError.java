package com.neriepam.learn.wxjava.weaver.error;

import com.neriepam.learn.wxjava.weaver.util.JsonUtil;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OaError implements Serializable {
    private static final long serialVersionUID = -509365369560L;

    private boolean status;

    private int code;

    private String msg;

    private String msgShowType;

    private String json;

    public static OaError fromJson(String json) {
        return JsonUtil.readValue(json, OaError.class);
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        if (json == null) {
            return "错误代码：" + code + ", 信息类型：" + msgShowType + ", 错误信息：" + msg;
        }
        return "错误代码：" + code + ", 信息类型：" + msgShowType + ", 错误信息：" + msg + ", 原始报文" + json;
    }
}
