package mrsisa.projekat.rezervacija;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pacijent pacijent;

    @ManyToOne(fetch = FetchType.EAGER)
    private Apoteka apoteka;

    @Column( nullable = false)
    private LocalDateTime datumRezervacije;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> rezervisaniLijekovi;

    @Column(nullable = false)
    private boolean izdato;

    @Column(nullable = false)
    private boolean odustao;

    public Rezervacija(long id, Pacijent pacijent, Apoteka apoteka, ArrayList<StanjeLijeka> rezervisaniLijekovi, LocalDateTime datumRezervacije) {
        this.id = id;
        this.pacijent = pacijent;
        this.apoteka = apoteka;
        this.rezervisaniLijekovi = rezervisaniLijekovi;
        this.datumRezervacije = datumRezervacije;
    }

    public Rezervacija() {

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

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public List<StanjeLijeka> getRezervisaniLijekovi() {
        return rezervisaniLijekovi;
    }

    public LocalDateTime getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDateTime datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public void setRezervisaniLijekovi(List<StanjeLijeka> rezervisaniLijekovi) {
        this.rezervisaniLijekovi = rezervisaniLijekovi;
    }

    public boolean isIzdato() {
        return izdato;
    }

    public void setIzdato(boolean izdato) {
        this.izdato = izdato;
    }

    public boolean isOdustao() {
        return odustao;
    }

    public void setOdustao(boolean odustao) {
        this.odustao = odustao;
    }
}
