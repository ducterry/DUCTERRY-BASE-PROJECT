package com.ducterry.base.enums;

public enum ErrorStatus {
    DO_SERVICE_SUCCESSFUL(1000, "Thành công"),
    UNHANDLED_ERROR(1004, "Lỗi hệ thống. Vui lòng thử lại"),
    INVALID_USER_EXISTED(1003, "USER đã tồn tại!"),
    INVALID_ROLE(1002, "ROLE không  tồn tại!"),
    USER_NOT_FOUND(1001, "Không tìm thấy User!"),
    LOGIN_PASSWORD_INVALID(1000, "Passwork không đúng");


    public int code;
    public String message;


    ErrorStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}