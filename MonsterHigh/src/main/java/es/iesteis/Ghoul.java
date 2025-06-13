package es.iesteis;

import java.util.HashMap;

public class Ghoul extends Monstruito{

    public Ghoul(String nome, Especies especie, String colorPiel, String colorPelo, boolean colmillos, boolean gafas, boolean alas, HashMap<String, Integer> habilidadesEspeciais) {
        super(nome, especie, colorPiel, colorPelo, colmillos, gafas, alas, habilidadesEspeciais);
    }

    @Override
    public void describir(){
        System.out.println("Soy " + getNome() + ", un "+ getEspecie() + "de piel " + getColorPiel() + " y cabello " + getColorPelo());
    }
}
