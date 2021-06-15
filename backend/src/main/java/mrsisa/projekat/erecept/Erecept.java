package mrsisa.projekat.erecept;

import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Proxy(lazy = false)
public class Erecept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sifra", unique = true) // NA OVO SE VRATITI
    private String sifra;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Pacijent pacijent;

    @Column(name = "datumIzdavanja", nullable = false)
    private LocalDateTime datumIzdavanja;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> prepisaniLijekovi;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Poseta poseta;

    @Column(name="izdato",nullable = false)
    private boolean izdato;

    public boolean isIzdato() {
        return izdato;
    }

    public void setIzdato(boolean izdato) {
        this.izdato = izdato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Poseta getPoseta() {
        return poseta;
    }

    public void setPoseta(Poseta poseta) {
        this.poseta = poseta;
    }

    public Erecept() {
        this.prepisaniLijekovi = new ArrayList<>();
    }

    public Erecept(String sifra, Pacijent pacijent, LocalDateTime datumIzdavanja, List<StanjeLijeka> prepisaniLijekovi, Poseta poseta) {
        this.sifra = sifra;
        this.pacijent = pacijent;
        this.datumIzdavanja = datumIzdavanja;
        this.prepisaniLijekovi = prepisaniLijekovi;
        this.poseta = poseta;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public LocalDateTime getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDateTime datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public List<StanjeLijeka> getPrepisaniLijekovi() {
        return prepisaniLijekovi;
    }

    public void setPrepisaniLijekovi(List<StanjeLijeka> prepisaniLijekovi) {
        this.prepisaniLijekovi = prepisaniLijekovi;
    }
}
