package cl.antoane.ufcapp_backend.usuario;

import cl.antoane.ufcapp_backend.usuario.dto.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public AuthResponse registrar(RegistroRequest req) {
        if (repo.existsByCorreo(req.correo())) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }
        var u = new UsuarioEntity();
        u.setCorreo(req.correo());
        u.setPasswordHash(encoder.encode(req.contrasena()));
        var saved = repo.save(u);
        return new AuthResponse(saved.getId(), saved.getCorreo());
    }

    public AuthResponse login(LoginRequest req) {
        var u = repo.findByCorreo(req.correo())
                .orElseThrow(() -> new IllegalArgumentException("El correo no está registrado."));

        if (!encoder.matches(req.contrasena(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }
        return new AuthResponse(u.getId(), u.getCorreo());
    }
}
