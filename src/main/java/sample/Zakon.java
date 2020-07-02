package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zakon implements Serializable {
    private String nazwa;
    private int idZakon;
    public static List<Zakon> listaZakon=new ArrayList<Zakon>();
    public List<Jedi> listaZakonJedi;

    {
        listaZakonJedi= new ArrayList<Jedi>();
    }

    public void dodajJedi(Jedi j){
        listaZakonJedi.add(j);
    }

    public void setIdZakon(int idZakon) {
        this.idZakon = idZakon;
    }

    public int getIdZakon() {
        return idZakon;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Zakon(String nazwa){
        this.nazwa=nazwa;
        listaZakon.add(this);

    }
    public Zakon(String nazwa, int idZakon){
        this.nazwa=nazwa;
        this.idZakon=idZakon;
        listaZakon.add(this);

    }

    @Override
    public String toString() {
        return "Zakon :" +
                 nazwa + '\'' +
                ", Ilość Jedi w zakonie: " + listaZakonJedi.size();
    }
}
