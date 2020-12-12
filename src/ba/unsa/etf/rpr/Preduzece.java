package ba.unsa.etf.rpr;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Preduzece {
    private Integer osnovica;
    private ArrayList<RadnoMjesto> radnaMjesta = new ArrayList<>();
    public Preduzece(Integer osnovica) throws NeispravnaOsnovica {
        this.postaviOsnovicu(osnovica);
    }

    public Integer dajOsnovicu() {
        return osnovica;
    }

    public void postaviOsnovicu(Integer osnovica) throws NeispravnaOsnovica {
        if(osnovica <=0) throw new NeispravnaOsnovica ("Neispravna osnovica " + osnovica);
        this.osnovica = osnovica;
    }


    public void novoRadnoMjesto(RadnoMjesto rm) {
        radnaMjesta.add(rm);
    }

    public Map<RadnoMjesto, Integer> sistematizacija() {
        Map<RadnoMjesto,Integer> mapaRadnihMjesta = new HashMap<>();
        for(RadnoMjesto value : radnaMjesta){
            mapaRadnihMjesta.put(value,0);
        }
        radnaMjesta.stream().forEach(radnoMjesto ->
                    mapaRadnihMjesta.put(radnoMjesto,mapaRadnihMjesta.get(radnoMjesto)+1)
                );
        return mapaRadnihMjesta;
    }

    public void zaposli(Radnik radnik, String naziv) {
        System.out.println(radnaMjesta.size());
        Optional<RadnoMjesto> radnoMjesto = radnaMjesta.stream().
                filter(radnMjesto -> radnMjesto.getRadnik()==null && radnMjesto.getNaziv().equals(naziv)).
                findFirst();

        if(radnoMjesto.isEmpty()){
            throw new IllegalStateException("Nijedno radno mjesto tog tipa nije slobodno");
        }
        radnoMjesto.get().setRadnik(radnik);
    }

    public Set<Radnik> radnici() {
        Set<Radnik> zaposleniRadnici;

        zaposleniRadnici = radnaMjesta.stream().filter(radnoMjesto -> radnoMjesto.getRadnik()!=null).
                map(radnoMjesto -> radnoMjesto.getRadnik()).
                collect(Collectors.toSet());

        return zaposleniRadnici;
    }

    public int iznosPlate() {
        Integer a=0;
        for(RadnoMjesto value : radnaMjesta) {
            if (value.getRadnik() != null) {
                a = a + value.getKoeficijent() * osnovica;
            }
        }
        return a;
    }

    public void obracunajPlatu() {
        radnaMjesta.stream().filter(radnoMjesto -> radnoMjesto.getRadnik()!=null).
                forEach(radnoMjesto ->
                        radnoMjesto.getRadnik().dodajPlatu(osnovica*radnoMjesto.getKoeficijent())
                );
    }

    public List<Radnik> filterRadnici(Predicate<Radnik> funkcija) {
        return radnaMjesta.stream().filter(radnoMjesto ->radnoMjesto.getRadnik()!=null && funkcija.test(radnoMjesto.getRadnik())).
                map(radnoMjesto -> radnoMjesto.getRadnik())
                .collect(Collectors.toList());
    }

    public List<Radnik> vecaProsjecnaPlata(int i) {
        return filterRadnici(radnik -> { return radnik.prosjecnaPlata()>i;});
    }
}
