package com.example.icloud_work.Exception;

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String msg;
    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
