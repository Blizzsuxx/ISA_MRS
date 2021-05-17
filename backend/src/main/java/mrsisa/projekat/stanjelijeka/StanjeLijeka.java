package mrsisa.projekat.stanjelijeka;


import com.fasterxml.jackson.annotation.JsonIgnore;
import mrsisa.projekat.KategorijaKorisnika.Kategorija;
import mrsisa.projekat.akcija.Akcija;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.narudzbenica.Narudzbenica;
import mrsisa.projekat.rezervacija.Rezervacija;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="stanje_lijeka")
public class StanjeLijeka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lijek lijek;
    @Column(name = "kolicina", nullable = false)
    private int kolicina;
    @Column(name = "prodaja", nullable = false)
    private boolean prodaja;
    @Column(name = "cijena", nullable = true)
    private double cijena;

    @Column(name = "datumIstekaCijene", nullable = true)
    private LocalDateTime datumIstekaCijene;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Apoteka apoteka;
    @ManyToOne(fetch = FetchType.LAZY)
    private Erecept eRecept;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rezervacija rezervacija;

    @ManyToOne(fetch = FetchType.LAZY)
    private Narudzbenica narudzbenica;


    @OneToOne(fetch=FetchType.LAZY)
    private Akcija akcija;

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public LocalDateTime getDatumIstekaCijene() {
        return datumIstekaCijene;
    }

    public void setDatumIstekaCijene(LocalDateTime datumTrajanjaCijene) {
        this.datumIstekaCijene = datumTrajanjaCijene;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lijek getLijek() {
        return lijek;
    }

    public void setLijek(Lijek lijek) {
        this.lijek = lijek;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public boolean isProdaja() {
        return prodaja;
    }

    public void setProdaja(boolean prodaja) {
        this.prodaja = prodaja;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public Erecept geteRecept() {
        return eRecept;
    }

    public void seteRecept(Erecept eRecept) {
        this.eRecept = eRecept;
    }

    public StanjeLijeka(Lijek lijek, int kolicina, boolean prodaja) {
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
    }

    public StanjeLijeka(Lijek lijek, int kolicina, boolean prodaja,LocalDateTime datumIstekaCijene) {
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
        this.datumIstekaCijene = datumIstekaCijene;
    }

    public StanjeLijeka(Long id, Lijek lijek, int kolicina, boolean prodaja,double cijena,LocalDateTime vrijemeIsteka) {
        this.id = id;
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
        this.cijena = cijena;
        this.datumIstekaCijene= vrijemeIsteka;
    }

    public StanjeLijeka(Long id, Lijek lijek, int kolicina, boolean prodaja,double cijena,LocalDateTime vrijemeIsteka,Apoteka apoteka) {
        this.id = id;
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
        this.cijena = cijena;
        this.datumIstekaCijene= vrijemeIsteka;
        this.apoteka = apoteka;
    }
    public StanjeLijeka(){

    }

    public StanjeLijeka(Lijek lijek, int kolicina, boolean prodaja, Apoteka apoteka, Erecept eRecept) {
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
        this.apoteka = apoteka;
        this.eRecept = eRecept;
    }

    public Akcija getAkcija() {
        return akcija;
    }

    public void setAkcija(Akcija akcija) {
        this.akcija = akcija;
    }
}
