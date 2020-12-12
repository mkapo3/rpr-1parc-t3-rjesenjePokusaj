package ba.unsa.etf.rpr;

import java.util.Objects;

public class RadnoMjesto {
    private String naziv;
    private Integer koeficijent;
    private Radnik radnik = null;

    public RadnoMjesto() {
    }

    public RadnoMjesto(String naziv, Integer koeficijent, Radnik radnik) {
        this.naziv = naziv;
        this.koeficijent = koeficijent;
        this.radnik = radnik;
    }

    public String getNaziv() {
        return naziv;
    }

    public Integer getKoeficijent() {
        return koeficijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setKoeficijent(Integer koeficijent) {
        this.koeficijent = koeficijent;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadnoMjesto that = (RadnoMjesto) o;
        return Objects.equals(naziv, that.naziv) &&
                Objects.equals(koeficijent, that.koeficijent) &&
                Objects.equals(radnik, that.radnik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, koeficijent, radnik);
    }
}
