package cl.antoane.ufcapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "peleadores")
public class Peleador {

    @Id
    private Integer id;

    private String nombre;
    private Integer victorias;
    private Integer derrotas;
    private Integer empates;
    private String division;
    private String imagenUrl;
    private Integer ranking;

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getVictorias() { return victorias; }
    public Integer getDerrotas() { return derrotas; }
    public Integer getEmpates() { return empates; }
    public String getDivision() { return division; }
    public String getImagenUrl() { return imagenUrl; }
    public Integer getRanking() { return ranking; }
}

