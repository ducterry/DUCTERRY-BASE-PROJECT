package com.ducterry.base.ducterrybase.dto.base;


import com.ndangducbn.base.dao.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseObject<T> {
    private Boolean result;
    private Integer code;
    private String message;
    private T data;
    private PaginationDTO paging;

    public ResponseObject() {
        //Constructor
    }

    private ResponseObject(Boolean result) {
        this.result = result;
    }

    public ResponseObject(Boolean result, ResponseStatus status) {
        this.result = result;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResponseObject(Boolean result, ResponseStatus status, String message) {
        this.result = result;
        this.code = status.getCode();
        this.message = message;
    }

    public ResponseObject(Boolean result, ResponseStatus status, T data) {
        this.result = result;
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public ResponseObject(Boolean result, int code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public ResponseObject(Boolean result, ResponseStatus status, T data, PaginationDTO paging) {
        this.result = result;
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
        this.paging = paging;
    }
}
