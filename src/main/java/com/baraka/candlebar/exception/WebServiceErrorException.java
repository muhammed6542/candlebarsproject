package com.baraka.candlebar.exception;


public class WebServiceErrorException extends IllegalArgumentException {
    public WebServiceErrorException() {
        super("Sorry, error in  web service");
    }

}

