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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Radnik radnik;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "kraj", nullable = false)
    private LocalDateTime kraj;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    //@OneToMany(mappedBy = "poseta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private List<Erecept> erecepti;

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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return kraj;
    }

    public void setEnd(LocalDateTime end) {
        this.kraj = end;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }


}
