package es.iesteis;

public class Especies{
    private String especie; // aqui hablamos de las subespecies ya que especies principales hay solo tres
    private String descripcion;

    public Especies(String especie, String descripcion) {
        this.especie = especie;
        this.descripcion = descripcion;
    }

    public String getEspecie() {
        return especie;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Especies{" +
                ", especie='" + especie + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}