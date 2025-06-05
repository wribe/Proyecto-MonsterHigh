package es.iesteis;

import java.util.HashMap;

public class Momia extends Monstruito {
    private int lonxitudeVendas;

    public Momia(String nome, int lonxitudeVendas) {
        super(nome);
        this.lonxitudeVendas = lonxitudeVendas;
    }

    @Override
    public void presentarse(){
        System.out.println("Hola, son unha momia, chámome " + getNome());
    }

    @Override
    public String toString() {
        return super.toString() +"Momia{" +
                "lonxitudeVendas=" + lonxitudeVendas +
                '}';
    }
}
