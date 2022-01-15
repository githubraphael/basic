package com.phiz.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhizException extends RuntimeException {
    //    private static final int INTERNAL_ERROR_CODE = HttpStatus.INTERNAL_SERVER_ERROR;
    HttpStatus responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    String errorMsg;
    String errCode;
    String errorSource;
    String id;
    private String entityName;

    private PhizException(String errorMsg, Throwable th) {
        super(errorMsg, th);
        this.errorMsg = errorMsg;
        this.responseStatusCode = HttpStatus.BAD_REQUEST;
    }

    public static class Builder {
        private HttpStatus responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        private String errorMsg;
        private String errCode;
        private String errorSource;
        private String id;
        private String entityName;
        private Throwable throwable;

        public Builder(String message) {
            this.errorMsg = message;
        }

        public Builder fromException(Throwable th) {
            this.throwable = th;
            return this;
        }

        public Builder withStatus(HttpStatus httpStatus) {
            this.responseStatusCode = httpStatus;
            return this;
        }

        public Builder forId(Object id) {
            if (id != null) {
                this.id = id.toString();
            }
            return this;
        }

        public Builder errCode(String errCode) {
            this.errCode = errCode;
            return this;
        }

        public Builder errorSource(String errorSource) {
            this.errorSource = errorSource;
            return this;
        }

        public Builder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        public PhizException build() {
            PhizException e = new PhizException(this.errorMsg, throwable);
            e.errorMsg = this.errorMsg;
            e.responseStatusCode = this.responseStatusCode;
            e.id = this.id;
            e.errCode = this.errCode;
            e.errorSource = this.errorSource;
            e.entityName = this.entityName;
            return e;
        }
    }
}
