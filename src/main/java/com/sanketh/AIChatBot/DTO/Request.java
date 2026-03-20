package com.sanketh.AIChatBot.DTO;

public record Request(
        String model,
        String prompt,
        boolean stream
) {}