package com.ndangducbn.base.dao.enums;


public enum ResponseStatus {
    DO_SERVICE_SUCCESSFUL(1000, "Thành công"),
    UNHANDLED_ERROR(1004, "Lỗi hệ thống. Vui lòng thử lại");

    public int code;
    public String message;
    private String messageFormat;

    ResponseStatus(int code, String message, String messageFormat) {
        this.code = code;
        this.message = message;
        this.messageFormat = messageFormat;
    }

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseStatus formatMessage(Object... str) {
        if (this.messageFormat != null) {
            this.message = String.format(this.messageFormat, str);
        }
        return this;
    }

    public ResponseStatus[] getListResponseStatus() {
        return ResponseStatus.values();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageFormat() {
        return messageFormat;
    }
}

