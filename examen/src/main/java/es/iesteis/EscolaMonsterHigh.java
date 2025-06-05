package es.iesteis;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class EscolaMonsterHigh {
    private ArrayList<Monstruito> estudantes;
    private LocalDate finDeCurso;

    public EscolaMonsterHigh(LocalDate finDeCurso) throws FindeCursoAnteriorException {
        this.estudantes = new ArrayList<>();
        if (dataCorrecta(finDeCurso)) {
            this.finDeCurso = finDeCurso;
        } else {
            throw new FindeCursoAnteriorException("A data que puseches é do curso anterior");
        }
    }

    private boolean dataCorrecta(LocalDate finDeCurso){
        Period periodo = Period.between(LocalDate.now(), finDeCurso);
        if (periodo.isNegative()){
            return false;
        } return true;
    }

    public String diasAtaFinDeCurso(){
        return "Faltan: " + Period.between(LocalDate.now(), finDeCurso).getYears() + " años, " + Period.between(LocalDate.now(), finDeCurso).getMonths() + " meses, " + Period.between(LocalDate.now(), finDeCurso).getDays() + " dias";
    }

    public void matricularEstudante(Monstruito estudante) throws EstudanteDuplicadoException {
        if (estudantes.isEmpty()){
            estudantes.add(estudante);
        }else {
            for (Monstruito e : estudantes) {
                if (e.equals(estudante)) {
                    throw new EstudanteDuplicadoException("Este estudante xa está matriculado na escola");
                }
            }
            estudantes.add(estudante);
        }
    }

    public void organizarFestaMonstruosa(){
        for (Monstruito estudante: estudantes){
            if (estudante instanceof Vampiro){
                ((Vampiro) estudante).presentarse();
            } else if (estudante instanceof Momia) {
                ((Momia) estudante).presentarse();
            } else if (estudante instanceof Licantropo) {
                ((Licantropo) estudante).presentarse();
            }
        }
    }

    public void tranformarEstudantes(){
        for (Monstruito estudante: estudantes){
            if (estudante instanceof Vampiro && ((Vampiro) estudante).poderTransformarse()){
                ((Vampiro) estudante).transformasrse();
            } else if (estudante instanceof Licantropo && ((Licantropo) estudante).poderTransformarse()) {
                ((Licantropo) estudante).transformasrse();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder cadeaEstudiantes = new StringBuilder();
        for (Monstruito estudante: estudantes){
            cadeaEstudiantes.append(estudante);
        }
        return "EscolaMonsterHigh{" +
                "estudantes=" + cadeaEstudiantes +
                ", finDeCurso=" + finDeCurso +
                '}';
    }
}
