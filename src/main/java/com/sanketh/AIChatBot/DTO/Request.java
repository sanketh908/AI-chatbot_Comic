package com.sanketh.AIChatBot.DTO;

import jakarta.validation.constraints.NotBlank;

public record Request(
        @NotBlank
        String model,
        @NotBlank
        String prompt,
        @NotBlank
        boolean stream
) {}