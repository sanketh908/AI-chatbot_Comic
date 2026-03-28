package com.sanketh.AIChatBot.Exception;

public class InvalidJWTToken extends RuntimeException {
    public InvalidJWTToken(String message) {
        super(message);
    }
}
