package com.phiz.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private String errorMessage;
    private String errorCode;
    private String debugMessage;
    private String errorSource;
    private String resourceId;
}
