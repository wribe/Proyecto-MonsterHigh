package es.iesteis;

public class Licantropo extends Monstruito {
    private Forma forma = Forma.NORMIE;;
    private FaseLunar faseLunar;

    public Licantropo(String nome) {
        super(nome);
        this.faseLunar = FaseLunar.NOVA;
    }

    public void ouvear() throws NonLuaCheaException {
        if (faseLunar.equals(FaseLunar.CHEA)){
            System.out.println("AUUUUUUUUUU");
        } else {
            throw new NonLuaCheaException("Non hai lúa chea, non podes ouvear a ela");
        }
    }

    @Override
    public void presentarse(){
        System.out.println("Ola, on un licántropo e chámome " + getNome());
    }

    public void setFaseLunar(FaseLunar faseLunar) {
        this.faseLunar = faseLunar;
    }


    @Override
    public String toString() {
        return super.toString() +"Licantropo{" +
                "forma=" + forma +
                ", faseLunar=" + faseLunar +
                '}';
    }
}
