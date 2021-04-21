package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.radnik.Radnik;

import java.util.List;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Radnik radnik;

    @Column(name = "pocetak", nullable = false)
    private LocalDateTime pocetak;

    @Column(name = "kraj", nullable = false)
    private LocalDateTime kraj;



    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    public Poseta() {

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

    @OneToMany(mappedBy = "poseta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Erecept> erecepti;



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


}
