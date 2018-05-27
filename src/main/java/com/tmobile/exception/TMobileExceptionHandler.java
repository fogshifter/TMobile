package com.tmobile.exception;

import com.tmobile.dto.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TMobileExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TMobileException.class)
    public StatusDTO handleEntryNotFound(TMobileException e) {
        return new StatusDTO(StatusDTO.StatusCode.ERROR, e.getMessage());
    }

}
