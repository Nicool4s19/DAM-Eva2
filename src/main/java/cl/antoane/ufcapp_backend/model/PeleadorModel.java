package cl.antoane.ufcapp_backend.model;

public class PeleadorModel {
    private Long id;
    private String nombre;
    private int victorias;
    private int derrotas;
    private int empates;
    private String division;
    private int ranking;
    private String imagenUrl;

    public PeleadorModel() {
    }

    public PeleadorModel(Long id, String nombre,
                         int victorias, int derrotas, int empates,
                         String division, int ranking, String imagenUrl) {
        this.id = id;
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.division = division;
        this.ranking = ranking;
        this.imagenUrl = imagenUrl;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getVictorias() { return victorias; }
    public void setVictorias(int victorias) { this.victorias = victorias; }

    public int getDerrotas() { return derrotas; }
    public void setDerrotas(int derrotas) { this.derrotas = derrotas; }

    public int getEmpates() { return empates; }
    public void setEmpates(int empates) { this.empates = empates; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public int getRanking() { return ranking; }
    public void setRanking(int ranking) { this.ranking = ranking; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}

