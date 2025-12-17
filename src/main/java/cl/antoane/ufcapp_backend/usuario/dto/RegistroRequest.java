package cl.antoane.ufcapp_backend.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroRequest(
        @Email @NotBlank String correo,
        @NotBlank @Size(min = 6) String contrasena
) {}
