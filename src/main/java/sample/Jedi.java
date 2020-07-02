package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jedi implements Serializable {

    public static List<Jedi> listaJedi=new ArrayList<Jedi>();


    private String nazwa;
    private KolorMiecza kolor;
    private int moc;
    private Boolean czyJasna;
    private Zakon zakon;
    private int idJedi;

    public Jedi(String nazwa, KolorMiecza kolor, int moc, Boolean czyJasna){
        this.nazwa=nazwa;
        this.kolor=kolor;
        this.moc=moc;
        this.czyJasna=czyJasna;
        listaJedi.add(this);
    }
    public Jedi(String nazwa, KolorMiecza kolor, int moc, Boolean czyJasna,int idJedi){
        this.nazwa=nazwa;
        this.kolor=kolor;
        this.moc=moc;
        this.czyJasna=czyJasna;
        this.idJedi=idJedi;
        listaJedi.add(this);
    }

    public void setIdJedi(int idJedi) {
        this.idJedi = idJedi;
    }

    public String getNazwa() {
        return nazwa;
    }

    public KolorMiecza getKolor() {
        return kolor;
    }

    public int getMoc() {
        return moc;
    }

    public Boolean getCzyJasna() {
        return czyJasna;
    }

    public Zakon getZakon() {
        return zakon;
    }

    public void setZakon(Zakon zakon) {
        this.zakon = zakon;
    }

    public int getIdJedi() {
        return idJedi;
    }

    @Override
    public String toString() {
        if(zakon==null){
            return "Jedi{" +
                    "nazwa='" + nazwa + '\'' +
                    ", kolor=" + kolor +
                    ", moc=" + moc +
                    ", czyJasna=" + czyJasna +
                    '}';
            }
        else {
            return "Jedi{" +
                    "nazwa='" + nazwa + '\'' +
                    ", kolor=" + kolor +
                    ", moc=" + moc +
                    ", czyJasna=" + czyJasna +
                    ", zakon=" + zakon.getNazwa() +
                    '}';
            }
    }
}
