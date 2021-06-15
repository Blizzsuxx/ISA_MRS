package mrsisa.projekat.poseta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.radnik.Radnik;
import org.hibernate.annotations.Proxy;

import java.util.List;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="poseta")
@Proxy(lazy = false)
public class Poseta {

    public Poseta(Long id, Pacijent pacijent, Radnik radnik, LocalDateTime pocetak, LocalDateTime kraj, Apoteka apoteka, List<Erecept> erecepti) {
        this.id = id;
        this.pacijent = pacijent;
        this.radnik = radnik;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.apoteka = apoteka;
        this.erecepti = erecepti;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Radnik radnik;

    @Column(nullable = true)
    private double cena;

    @Column(name = "pocetak", nullable = false)
    private LocalDateTime pocetak;

    @Column(name = "otkazano", nullable = true)
    private Boolean otkazano = null; // null - nije pregledan true znaci otkazano, false znaci pregledano

    @Column(name="opis", nullable = true)
    private String opis;

    @Column(name = "kraj", nullable = false)
    private LocalDateTime kraj;

    @OneToMany(mappedBy = "poseta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Erecept> erecepti;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    public Poseta() {

    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalDateTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalDateTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalDateTime kraj) {
        this.kraj = kraj;
    }

    public List<Erecept> getErecepti() {
        return erecepti;
    }

    public void setErecepti(List<Erecept> erecepti) {
        this.erecepti = erecepti;
    }





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



    public Apoteka getApoteka() {
        return apoteka;

    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }


    public Boolean getOtkazano() {
        return otkazano;
    }

    public void setOtkazano(Boolean otkazano) {
        this.otkazano = otkazano;
    }
}
