package com.fazo.esm.exception;

import com.fazo.esm.payload.ErrorData;
import com.fazo.esm.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ApiResponse<ErrorData>> handleRestException(RestException ex) {
        ErrorData errorData = new ErrorData(ex.getMessage());
        ApiResponse<ErrorData> response = ApiResponse.errorResponse(Collections.singletonList(errorData));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorData>> handleOtherExceptions(Exception ex) {
        ErrorData errorData = new ErrorData("Internal Server Error");
        ApiResponse<ErrorData> response = ApiResponse.errorResponse(Collections.singletonList(errorData));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
