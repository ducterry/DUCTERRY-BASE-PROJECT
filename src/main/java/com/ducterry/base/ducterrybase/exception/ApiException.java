package com.ducterry.base.ducterrybase.exception;


import com.ndangducbn.base.dao.enums.ResponseStatus;

public class ApiException extends RuntimeException {
    private int code;
    private final ResponseStatus errorStatus;

    public ApiException(ResponseStatus errorStatus, String msg) {
        super(msg);
        this.errorStatus = errorStatus;
    }

    public ApiException(ResponseStatus errorStatus) {
        super(errorStatus.getMessage());
        this.code = errorStatus.getCode();
        this.errorStatus = errorStatus;
    }
}
