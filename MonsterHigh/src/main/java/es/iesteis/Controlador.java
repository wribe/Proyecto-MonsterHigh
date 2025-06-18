package es.iesteis;

import java.util.ArrayList;
import java.util.HashMap;

public class Controlador {
    private final DAO dao;

    public Controlador() {
        this.dao = new DAO("jdbc:mysql://localhost:3306/monster_high", "root", "122436");
    }

    public HashMap<String, Monstruito> buscarMonstruito(boolean colmillos, boolean gafas, boolean zonmbie, String especie){
        return dao.devolverMonstruito(colmillos, gafas, zonmbie, especie);
    }

    public ArrayList<Monstruito> buscarMonstruitoFiltrado(boolean colmillos, boolean gafas, boolean zonmbie, String especie){
        return dao.devolverMonstruitoFiltrado(colmillos, gafas, zonmbie, especie);
    }
}
