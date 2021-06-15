package mrsisa.projekat.slobodanTermin;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class SlobodanTermin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime pocetakTermina;
    private LocalDateTime krajTermina;
    private double cijenaTermina;
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version;

    @ManyToOne( fetch = FetchType.LAZY)
    private Radnik radnik;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    public SlobodanTermin(Poseta poseta, Long id) {
        this.id=id;
        this.radnik= poseta.getRadnik();
        this.pocetakTermina=poseta.getPocetak();
        this.krajTermina=poseta.getKraj();
        //this.cijenaTermina=poseta.getCena(); TODO razmisliti o dodavanju apoteke u ovu klasu
    }
    public SlobodanTermin(Poseta poseta) {

        this.radnik= poseta.getRadnik();
        this.pocetakTermina=poseta.getPocetak();
        this.krajTermina=poseta.getKraj();
        //this.cijenaTermina=poseta.getCena(); TODO razmisliti o dodavanju apoteke u ovu klasu
    }

    public SlobodanTermin() {
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getPocetakTermina() {
        return pocetakTermina;
    }

    public void setPocetakTermina(LocalDateTime pocetakTermina) {
        this.pocetakTermina = pocetakTermina;
    }

    public LocalDateTime getKrajTermina() {
        return krajTermina;
    }

    public void setKrajTermina(LocalDateTime krajTermina) {
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
