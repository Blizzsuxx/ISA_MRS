package mrsisa.projekat.radnoVrijeme;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class RadnoVrijeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dermatolog dermatolog;
    private LocalTime pocetakRadnogVremena;
    private LocalTime krajRadnogVremena;

    public RadnoVrijeme(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public Dermatolog getDermatolog() {
        return dermatolog;
    }

    public void setDermatolog(Dermatolog dermatolog) {
        this.dermatolog = dermatolog;
    }

    public LocalTime getPocetakRadnogVremena() {
        return pocetakRadnogVremena;
    }

    public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
        this.pocetakRadnogVremena = pocetakRadnogVremena;
    }

    public LocalTime getKrajRadnogVremena() {
        return krajRadnogVremena;
    }

    public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
        this.krajRadnogVremena = krajRadnogVremena;
    }
}
