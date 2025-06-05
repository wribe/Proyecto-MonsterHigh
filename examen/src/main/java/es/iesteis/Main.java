package es.iesteis;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        Vampiro v = new Vampiro("carmen", 100);
        Vampiro vE = new Vampiro("carmen", 1005);
        Momia m = new Momia("andrew", 40);
        Licantropo l = new Licantropo("martuki");

        try {
            System.out.println(v);

            System.out.println(vE);
            vE.beberSangue();
            System.out.println(vE);
            vE.aprenderHabilidade("camiñar", 5);
            System.out.println(vE);
            //        vE.aprenderHabilidade("voar", 50);
            //        System.out.println(vE);
            v.presentarse();
            v.transformasrse();
            System.out.println(v);
            System.out.println(v.equals(vE));



            System.out.println(m);
            m.presentarse();



            System.out.println(l);
            l.setFaseLunar(FaseLunar.CHEA);
            System.out.println(l);
            l.presentarse();
            l.ouvear();
            l.transformasrse();
            System.out.println(l);
//            l.setFaseLunar(FaseLunar.CRECENTE);
//            l.ouvear();


            EscuelaMonsterHigh monsterHigh = new EscuelaMonsterHigh(LocalDate.of(2026, 06, 06));
            System.out.println(monsterHigh);
            System.out.println(monsterHigh.diasAtaFinDeCurso());
            monsterHigh.matricularEstudante(m);
            System.out.println(monsterHigh);
            monsterHigh.matricularEstudante(v);
            //        monsterHigh.matricularEstudante(v);
            monsterHigh.organizarFestaMonstruosa();
            monsterHigh.tranformarEstudantes();
            System.out.println(monsterHigh);

            //        EscuelaMonsterHigh monsterHigh2 = new EscuelaMonsterHigh(LocalDate.of(2024, 06, 06));
            //        System.out.println(monsterHigh2);
        } catch (NonLuaCheaException|EstudanteDuplicadoException|ValorDominioIncorrectoException|FindeCursoAnteriorException e) {
            e.getMessage();
        }
    }
}