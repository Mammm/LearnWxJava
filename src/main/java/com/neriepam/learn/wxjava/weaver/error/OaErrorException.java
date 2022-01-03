package com.neriepam.learn.wxjava.weaver.error;

public class OaErrorException extends Exception {
    private static final long serialVersionUID = -101L;

    private final OaError error;

    private static final int DEFAULT_ERROR_CODE = -1;

    public OaErrorException(String message) {
        this(OaError.builder().code(DEFAULT_ERROR_CODE).msg(message).status(false).build());
    }

    public OaErrorException(OaError error) {
        super(error.toString());
        this.error = error;
    }

    public OaErrorException(OaError error, Throwable e) {
        super(error.toString(), e);
        this.error = error;
    }

    public OaErrorException(Throwable e) {
        super(e.getMessage(), e);
        this.error = OaError.builder().code(DEFAULT_ERROR_CODE).msg(e.getMessage()).status(false).build();
    }

    public OaError getError() {
        return error;
    }

    public static OaErrorException emptyBody() {
        return new OaErrorException("报文为空");
    }
}
