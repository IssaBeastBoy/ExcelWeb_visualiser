package com.backend.backend.uploads;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class lookUPFileNotFoundException extends RuntimeException {
    public lookUPFileNotFoundException(String message){
        super(message);
    }

    public lookUPFileNotFoundException( String message, Throwable cause){
        super(cause);
    }

}
