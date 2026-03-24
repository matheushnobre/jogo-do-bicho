package br.com.bichofull.bichofull.infra.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityErrorDTO {
    @Schema(example = "2026-03-24T19:40:05.551Z")
    private String timestamp;

    @Schema(example = "403")
    private int status;

    @Schema(example = "Forbidden")
    private String error;

    @Schema(example = "/api/users/me")
    private String path;
}