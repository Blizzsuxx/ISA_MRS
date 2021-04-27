package mrsisa.projekat.slobodanTermin;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;

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
    private Dermatolog dermatolog;

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

    public Dermatolog getDermatolog() {
        return dermatolog;
    }

    public void setDermatolog(Dermatolog dermatolog) {
        this.dermatolog = dermatolog;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }
}
