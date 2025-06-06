package es.iesteis;

import java.util.HashMap;
import java.util.Objects;

public abstract class Monstruito {
    private  int codigo;
    private String nome;
    private HashMap<String, Integer> habilidadesEspeciais;

    public Monstruito(String nome, int codigo) {
        this.nome = nome;
        this.habilidadesEspeciais = new HashMap<>();
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public abstract void presentarse();

    public void aprenderHabilidade(String cadea, int dominio) throws ValorDominioIncorrectoException {
        if (habilidadesEspeciais.size() != 0) {
            for (String habilidade : habilidadesEspeciais.keySet()) {
                if (habilidade.equals(cadea)) {
                    if (dominio < 0 || dominio > 10) {
                        throw new ValorDominioIncorrectoException("Dominio nan válido");
                    } else {
                        habilidadesEspeciais.replace(cadea, dominio);
                    }
                } else {
                    habilidadesEspeciais.put(cadea, dominio);
                }
            }
        } else {
            habilidadesEspeciais.put(cadea, dominio);
        }

    }

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
                "codigo= '" + codigo + '\'' +
                "nome='" + nome + '\'' +
                ", habilidadesEspeciais=" + habilidadesEspeciais +
                " TIPO: ";
    }
}
