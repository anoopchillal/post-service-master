package com.example.postservice.exception;

import com.example.postservice.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class PostServiceExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PostNotFoundException.class})
    ResponseEntity postNotFoundHandler(Exception exception, ServletWebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setMessage(exception.getMessage());
        apiError.setCode("404");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
