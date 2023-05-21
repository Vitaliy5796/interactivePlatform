package ru.bashdev.interactivePlatform.util;

public class UserNotCreatedException extends RuntimeException{

    public UserNotCreatedException(String msg) {
        super(msg);
    }
}
