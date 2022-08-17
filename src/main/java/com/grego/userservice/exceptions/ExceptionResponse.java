package com.grego.userservice.exceptions;

import java.util.Date;

public class ExceptionResponse {

    private String message;


    public ExceptionResponse( String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
