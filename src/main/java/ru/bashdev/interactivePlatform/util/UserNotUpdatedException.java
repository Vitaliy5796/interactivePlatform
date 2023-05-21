package ru.bashdev.interactivePlatform.util;

public class UserNotUpdatedException extends RuntimeException{
    public UserNotUpdatedException(String msg) {
        super(msg);
    }
}
