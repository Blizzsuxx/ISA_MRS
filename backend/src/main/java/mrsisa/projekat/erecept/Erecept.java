package mrsisa.projekat.erecept;

import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Erecept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sifra", nullable = false, unique = true)
    private String sifra;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Pacijent pacijent;

    @Column(name = "datumIzdavanja", nullable = false)
    private LocalDateTime datumIzdavanja;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> prepisaniLijekovi;

    public Erecept() {}

    public Erecept(String sifra, Pacijent pacijent, LocalDateTime datumIzdavanja, List<StanjeLijeka> prepisaniLijekovi) {
        this.sifra = sifra;
        this.pacijent = pacijent;
        this.datumIzdavanja = datumIzdavanja;
        this.prepisaniLijekovi = prepisaniLijekovi;
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
