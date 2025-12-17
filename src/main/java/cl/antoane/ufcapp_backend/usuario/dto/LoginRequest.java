package cl.antoane.ufcapp_backend.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email @NotBlank String correo,
        @NotBlank String contrasena
) {}
