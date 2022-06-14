package com.baraka.candlebar.exception;


public class StockNotFoundException extends IllegalArgumentException {
    public StockNotFoundException() {
        super("Sorry, stock id  not found");
    }

}

