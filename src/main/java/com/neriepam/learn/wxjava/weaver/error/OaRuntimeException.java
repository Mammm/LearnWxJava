package com.neriepam.learn.wxjava.weaver.error;

public class OaRuntimeException extends RuntimeException {
    public OaRuntimeException(String msg) {
        super(msg);
    }
    public OaRuntimeException(Throwable e) {
        super(e);
    }
    public OaRuntimeException(String msg, Throwable e) {
        super(msg, e);
    }
}
