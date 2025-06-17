package es.iesteis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Vista {
    private final Controlador controlador = new Controlador();
    private final Scanner t = new Scanner(System.in);

    public void iniciar(){
        try {
            boolean colmillos;
            boolean gafas;
            boolean zombie;
            System.out.println("BIENVENIDX AL JUEGO MÁS MONSTRUOSO DE TODOS");
            System.out.println("¿Quién es quién? - Monster High 🕸️");
            System.out.println("EMPEZAMOS EL JUEGO...");
            System.out.println();
            System.out.println("¿Tu personaje tiene colmillos?");
            String c = t.nextLine();
            if (c.equalsIgnoreCase("si")) {
                colmillos = true;
            } else {
                colmillos = false;
            }
            System.out.println("¿Tu personaje usa lentes?");
            String l = t.nextLine();
            if (l.equalsIgnoreCase("si")) {
                gafas = true;
            } else {
                gafas = false;
            }
            System.out.println("¿Tu personaje es un zombie?");
            String z = t.nextLine();
            if (z.equalsIgnoreCase("si")) {
                zombie = true;
            } else {
                zombie = false;
            }

            HashMap<String, Monstruito> monstruitos = controlador.buscarMonstruito(colmillos, gafas, zombie);
            ArrayList<Monstruito> monstruitos1 = controlador.buscarMonstruitoFiltrado(colmillos, gafas, zombie);

            System.out.println("\nPersonajes posibles:");
            if (monstruitos.isEmpty()) {
                System.out.println("No se encontraron coincidencias.");
            } else {
//                for (String nombre : monstruitos.keySet()) {
//                    System.out.println(monstruitos.get(nombre));
//                    monstruitos.get(nombre).describir();
//                }
                for (Monstruito monstruito : monstruitos1) {
                    System.out.println(monstruito);
                    monstruito.describir();
                }
            }
        } catch (PersonajeNoEncontrado e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("❌ Error en los datos del personaje (verifica nombres o colores en la BD)");
        }

    }
}
