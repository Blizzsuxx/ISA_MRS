package mrsisa.projekat.rezervacija;

import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class RezervacijaDTO {
    private Long id;
    private String nazivLeka;
    private String Vrstaleka;
    private int kolicina;
    private double cena;
    private String nazivApoteke;
    private String datumVazenja;

    public RezervacijaDTO(Rezervacija r) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivLeka() {
        return nazivLeka;
    }

    public void setNazivLeka(String nazivLeka) {
        this.nazivLeka = nazivLeka;
    }

    public String getVrstaleka() {
        return Vrstaleka;
    }

    public void setVrstaleka(String vrstaleka) {
        Vrstaleka = vrstaleka;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazivApoteke() {
        return nazivApoteke;
    }

    public void setNazivApoteke(String nazivApoteke) {
        this.nazivApoteke = nazivApoteke;
    }

    public String getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(String datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public RezervacijaDTO(Long id,String nazivLeka, String vrstaleka, int kolicina, double cena, String nazivApoteke, String datumVazenja) {
        this.nazivLeka = nazivLeka;
        this.Vrstaleka = vrstaleka;
        this.kolicina = kolicina;
        this.cena = cena;
        this.nazivApoteke = nazivApoteke;
        this.datumVazenja = datumVazenja;
        this.id=id;
    }
    public RezervacijaDTO(Rezervacija r, StanjeLijeka l){
        this.nazivLeka=l.getLijek().getNaziv();
        this.Vrstaleka=l.getLijek().getVrstaLijeka();
        this.cena=l.getCijena();
        this.kolicina=l.getKolicina();
        this.nazivApoteke=r.getApoteka().getIme();
        DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        this.datumVazenja=r.getDatumRezervacije().format(df);
        this.id=r.getId();
    }
}
