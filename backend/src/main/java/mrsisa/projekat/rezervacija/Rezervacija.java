package mrsisa.projekat.rezervacija;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> rezervisaniLijekovi;

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

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public List<StanjeLijeka> getRezervisaniLijekovi() {
        return rezervisaniLijekovi;
    }

    public void setRezervisaniLijekovi(List<StanjeLijeka> rezervisaniLijekovi) {
        this.rezervisaniLijekovi = rezervisaniLijekovi;
    }
}
