package com.efficy.counterapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED)
public class InValidProductException extends RuntimeException {
    private static final long serialVersionUID = 6584480319956341884L;
    public InValidProductException(String message) {
        super(message);
    }
}
