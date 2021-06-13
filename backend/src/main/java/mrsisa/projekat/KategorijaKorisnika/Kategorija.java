package mrsisa.projekat.KategorijaKorisnika;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kategorija {
    @Column(name = "maxBrPoena", nullable = true)
    private int maxBrPoena; //broj poena koje korisnik mozemaximalno da osvoji sa ovom kategorijom
    @Column(name = "popust", nullable = true)
    private int popust;
    @Id
    @Column(name = "naziv", nullable = true)
    private String naziv;

    public int getMaxBrPoena() {
        return maxBrPoena;
    }

    public void setMaxBrPoena(int maxBrPoena) {
        this.maxBrPoena = maxBrPoena;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Kategorija() {
    }

    public Kategorija(int maxBrPoena, int popust, String naziv) {
        this.maxBrPoena = maxBrPoena;
        this.popust = popust;
        this.naziv = naziv;
    }
}
