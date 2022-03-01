package com.ducterry.base.exception;


import com.ducterry.base.enums.ErrorStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {
    private HttpStatus status;
    private ErrorStatus error;

    public ApiException(HttpStatus status, ErrorStatus error) {
        this.status = status;
        this.error = error;
    }
}
