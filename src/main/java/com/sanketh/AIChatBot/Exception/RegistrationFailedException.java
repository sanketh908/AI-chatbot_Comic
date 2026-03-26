package com.sanketh.AIChatBot.Exception;

import java.io.Serial;

public class RegistrationFailedException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;
    public RegistrationFailedException(String message)
    {
        super(message);
    }
}
