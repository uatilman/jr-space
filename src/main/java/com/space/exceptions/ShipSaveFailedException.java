package com.space.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ShipSaveFailedException extends RuntimeException {

    public ShipSaveFailedException() {
    }

    public ShipSaveFailedException(String message) {
        super(message);
    }
}
