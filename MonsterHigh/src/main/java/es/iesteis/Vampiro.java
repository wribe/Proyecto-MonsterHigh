package es.iesteis;

import java.util.HashMap;

public class Vampiro extends Monstruito implements Transformable{
    private Forma forma = Forma.NORMIE;
    private int nivelEnerxia;

    public Vampiro(String nome, int nivelEnerxia) {
        super(nome);
        if (enerxiaCorrecta(nivelEnerxia)) {
            this.nivelEnerxia = nivelEnerxia;
        } else {
            this.nivelEnerxia = 0;
        }
    }

    private boolean enerxiaCorrecta(int nivelEnerxia){
        if (nivelEnerxia < 0 || nivelEnerxia >100){
            return false;
        } return true;
    }

    public void beberSangue() {
        if (!(getNome().equals("Draculaura"))){
            if (nivelEnerxia == 100 || nivelEnerxia + 10 >= 100) {
                nivelEnerxia = 100;
            } else {
                nivelEnerxia += 10;
            }
        } else{
            System.out.println("Yo no puedo beber sangre, me desmayo!! Pero lo que voy a hacer es tomarme un rico tomate:)");
            if (nivelEnerxia == 100 || nivelEnerxia + 10 >= 100) {
                nivelEnerxia = 100;
            } else {
                nivelEnerxia += 10;
            }
        }
    }


    @Override
    public void presentarse(){
        System.out.println("Hola, son un vampiro, chámome " + getNome());
    }

    @Override
    public boolean poderTransformarse(){
        if (nivelEnerxia > 50){
            return true;
        } return false;
    }

    @Override
    public void transformasrse(){
        if (poderTransformarse() && forma.equals(Forma.NORMIE)){
            forma = Forma.MURCIELAGO;
            nivelEnerxia -= 50;
        } else {
            forma = Forma.NORMIE;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Vampiro{" +
                "forma=" + forma +
                ", nivelEnerxia=" + nivelEnerxia +
                '}';
    }
}
