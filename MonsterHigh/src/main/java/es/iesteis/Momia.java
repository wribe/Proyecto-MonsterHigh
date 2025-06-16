package es.iesteis;

import java.util.HashMap;

public class Momia extends Monstruito {

    public Momia(String nome, Especies especie, ColorPiel colorPiel, ColorPelo colorPelo, boolean colmillos, boolean gafas, boolean alas) {
        super(nome, especie, colorPiel, colorPelo, colmillos, gafas, alas);
    }

    @Override
    public void describir(){
        System.out.println("Soy " + getNome() + ", un "+ getEspecie() + "de piel " + getColorPiel() + " y cabello " + getColorPelo() + " y tengo vendas");
    }
}
