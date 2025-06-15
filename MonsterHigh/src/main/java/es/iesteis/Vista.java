package es.iesteis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Vista {
    private final Controlador controlador;
    private final Scanner t;

    public Vista() {
        this.controlador = new Controlador();
        this.t = new Scanner(System.in);
    }

    public void iniciar(){
        boolean colmillos;
        boolean gafas;
        boolean zombie;
        System.out.println("BIENVENIDX AL JUEGO MÁS MONSTRUOSO DE TODOS");
        System.out.println("¿Quién es quién? - Monster High 🕸️");
        System.out.println("EMPEZAMOS EL JUEGO...");
        System.out.println();
        System.out.println("¿Tu personaje tiene colmillos?");
        String c = t.nextLine();
        if (c.equalsIgnoreCase("si")){
            colmillos = true;
        }else {
            colmillos = false;
        }
        System.out.println("¿Tu personaje usa lentes?");
        String l = t.nextLine();
        if (l.equalsIgnoreCase("si")){
            gafas = true;
        }else {
            gafas = false;
        }
        System.out.println("¿Tu personaje es un zombie?");
        String z = t.nextLine();
        if (z.equalsIgnoreCase("si")){
            zombie = true;
        }else {
            zombie = false;
        }

        HashMap<String, Monstruito> monstruitos = controlador.buscarMonstruito(colmillos, gafas, zombie);

        System.out.println("\nPersonajes posibles:");
        if (monstruitos.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
        } else {
            for (String nombre: monstruitos.keySet()){
                System.out.println(monstruitos.get(nombre));
            }
        }

    }
}
