package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Radnik implements Comparable<Radnik> {
    private String imePrezime;
    private String jmbg;
    private ArrayList<Integer> plate = new ArrayList<>();

    public Radnik(String imePrezime, String jmbg) {
        this.imePrezime = imePrezime;
        this.jmbg = jmbg;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void dodajPlatu(int i) {
        if(plate.size()==1000) throw new IllegalArgumentException("Ne možete registrovati više od 1000 plata za radnika " + imePrezime);
        plate.add(i);
    }

    public double prosjecnaPlata() {
        if(plate.size()==0) return 0;
        return (double)plate.stream().reduce(0, Integer::sum)/plate.size();
    }

    @Override
    public int compareTo(Radnik o) {
       int poredjenje=Double.compare(this.prosjecnaPlata(),o.prosjecnaPlata());
       if(poredjenje==0){
           poredjenje=this.getImePrezime().compareTo(o.getImePrezime());
       }
       if(poredjenje==0)
           return -1;
       return poredjenje;
    }
}
