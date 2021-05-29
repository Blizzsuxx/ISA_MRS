package mrsisa.projekat.radnoVrijeme;


import mrsisa.projekat.KategorijaKorisnika.Kategorija;
import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.farmaceut.Farmaceut;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToOne(fetch=FetchType.LAZY)
    private Farmaceut farmaceuet;

    @Column(name = "pocetakRadnogVremena", nullable = false)
    private LocalDateTime pocetakRadnogVremena;

    @Column(name = "krajRadnogVremena", nullable = false)
    private LocalDateTime krajRadnogVremena;

    public RadnoVrijeme(){

    }

    public RadnoVrijeme(Apoteka apoteka, Dermatolog dermatolog, Farmaceut farmaceuet, LocalDateTime pocetakRadnogVremena, LocalDateTime krajRadnogVremena) {
        this.apoteka = apoteka;
        this.dermatolog = dermatolog;
        this.farmaceuet = farmaceuet;
        this.pocetakRadnogVremena = pocetakRadnogVremena;
        this.krajRadnogVremena = krajRadnogVremena;
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

    public LocalDateTime getPocetakRadnogVremena() {
        return pocetakRadnogVremena;
    }

    public void setPocetakRadnogVremena(LocalDateTime pocetakRadnogVremena) {
        this.pocetakRadnogVremena = pocetakRadnogVremena;
    }

    public LocalDateTime getKrajRadnogVremena() {
        return krajRadnogVremena;
    }

    public void setKrajRadnogVremena(LocalDateTime krajRadnogVremena) {
        this.krajRadnogVremena = krajRadnogVremena;
    }

    public Farmaceut getFarmaceuet() {
        return farmaceuet;
    }

    public void setFarmaceuet(Farmaceut farmaceuet) {
        this.farmaceuet = farmaceuet;
    }
}
