package cl.antoane.ufcapp_backend.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotBlank
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank
    @Column(nullable = false)
    private String passwordHash;

    public Long getId() { return id; }
    public String getCorreo() { return correo; }
    public String getPasswordHash() { return passwordHash; }

    public void setCorreo(String correo) { this.correo = correo; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
