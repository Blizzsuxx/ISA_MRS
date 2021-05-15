package mrsisa.projekat.slobodanTermin;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class SlobodanTermin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime pocetakTermina;
    private LocalTime krajTermina;
    private double cijenaTermina;

    @ManyToOne( fetch = FetchType.LAZY)
    private Radnik radnik;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;


    public void setId(Long id) {
        this.id = id;
    }



    public Long getId() {
        return id;
    }

    public LocalTime getPocetakTermina() {
        return pocetakTermina;
    }

    public void setPocetakTermina(LocalTime pocetakTermina) {
        this.pocetakTermina = pocetakTermina;
    }

    public LocalTime getKrajTermina() {
        return krajTermina;
    }

    public void setKrajTermina(LocalTime krajTermina) {
        this.krajTermina = krajTermina;
    }

    public double getCijenaTermina() {
        return cijenaTermina;
    }

    public void setCijenaTermina(double cijenaTermina) {
        this.cijenaTermina = cijenaTermina;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }
}
