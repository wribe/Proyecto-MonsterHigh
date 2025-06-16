package es.iesteis;

import java.util.HashMap;
import java.util.Objects;

public abstract class Monstruito implements Jugable{
    private String nome;
    private Especies especie;
    private ColorPiel colorPiel;
    private ColorPelo colorPelo;
    private boolean colmillos;
    private boolean gafas;
    private boolean alas;

    public Monstruito(String nome, Especies especie, ColorPiel colorPiel, ColorPelo colorPelo, boolean colmillos, boolean gafas, boolean alas) {
        this.nome = nome;
        this.especie = especie;
        this.colorPiel = colorPiel;
        this.colorPelo = colorPelo;
        this.colmillos = colmillos;
        this.gafas = gafas;
        this.alas = alas;
    }

    public String getNome() {
        return nome;
    }


    public Especies getEspecie() {
        return especie;
    }

    public ColorPiel getColorPiel() {
        return colorPiel;
    }

    public ColorPelo getColorPelo() {
        return colorPelo;
    }

    public boolean isColmillos() {
        return colmillos;
    }

    public boolean isGafas() {
        return gafas;
    }

    public boolean isAlas() {
        return alas;
    }

    public void presentarse(){
        System.out.println("Hola! Soy el monstruito que vas a tener que adivinar jejeje");
    }

//    public void aprenderHabilidade(String cadea, int dominio) throws ValorDominioIncorrectoException {
//        if (habilidadesEspeciais.size() != 0) {
//            for (String habilidade : habilidadesEspeciais.keySet()) {
//                if (habilidade.equals(cadea)) {
//                    if (dominio < 0 || dominio > 10) {
//                        throw new ValorDominioIncorrectoException("Dominio nan válido");
//                    } else {
//                        habilidadesEspeciais.replace(cadea, dominio);
//                    }
//                } else {
//                    habilidadesEspeciais.put(cadea, dominio);
//                }
//            }
//        } else {
//            habilidadesEspeciais.put(cadea, dominio);
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Monstruito estudante)) return false;
        return Objects.equals(nome, estudante.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString() {
        return "Estudiante " +
                "nome='" + nome + '\'' +
                ", Especie=" + especie;
    }
}
