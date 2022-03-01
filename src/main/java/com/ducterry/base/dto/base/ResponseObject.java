package com.ducterry.base.dto.base;


import com.ducterry.base.enums.ErrorStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result","code","message","data","paging"
})
public class ResponseObject<T> {
    private Boolean result;
    private Integer code;
    private String message;
    private T data;
    private PaginationDTO paging;



    public ResponseObject(Boolean result, ErrorStatus status) {
        this.result = result;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResponseObject(Boolean result, T data) {
        this.result = result;
        this.data = data;
    }


    public ResponseObject(Boolean result, ErrorStatus status, T data, PaginationDTO paging) {
        this.result = result;
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
        this.paging = paging;
    }
}
