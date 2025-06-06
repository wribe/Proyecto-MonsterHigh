package es.iesteis;

public class Especies{
    private int id;
    private String especie;
    private String descripcion;

    public Especies(int id, String especie, String descripcion) {
        this.id = id;
        this.especie = especie;
        this.descripcion = descripcion;
    }

    public String getEspecie() {
        return especie;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Especies{" +
                "id=" + id +
                ", especie='" + especie + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}