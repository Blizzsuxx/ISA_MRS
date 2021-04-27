package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PosetaDTO {

    private Long id;
    private Pacijent pacijent;
    private Radnik radnik;
    private String pocetak;
    private String kraj;
    private String opis;
    private Apoteka apoteka;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public PosetaDTO(Poseta p) {
        this.id = p.getId();
        this.pacijent = p.getPacijent();
        this.radnik = p.getRadnik();
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.pocetak = p.getPocetak().format(df);
        this.kraj = p.getKraj().format(df);
        this.opis = "opis";
        this.apoteka = p.getApoteka();
    }
    public PosetaDTO(){}
}
