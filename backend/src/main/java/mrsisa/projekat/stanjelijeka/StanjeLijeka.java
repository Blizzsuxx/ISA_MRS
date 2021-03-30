package mrsisa.projekat.stanjelijeka;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.rezervacija.Rezervacija;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="stanje_lijeka")
public class StanjeLijeka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Lijek lijek;
    @Column(name = "kolicina", nullable = false)
    private int kolicina;
    @Column(name = "prodaja", nullable = false)
    private boolean prodaja;
    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;
    @ManyToOne(fetch = FetchType.LAZY)
    private Erecept eRecept;
    private double cijena;
    private LocalDateTime datumIstekaCijene;
    @ManyToOne(fetch = FetchType.LAZY)
    private Rezervacija rezervacija;

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public LocalDateTime getDatumIstekaCijene() {
        return datumIstekaCijene;
    }

    public void setDatumIstekaCijene(LocalDateTime datumIstekaCijene) {
        this.datumIstekaCijene = datumIstekaCijene;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
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

    public StanjeLijeka(Long id, Lijek lijek, int kolicina, boolean prodaja,double cijena,LocalDateTime datum) {
        this.id = id;
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
        this.datumIstekaCijene = datum;
        this.cijena  = cijena;
    }
    public StanjeLijeka(Long id, Lijek lijek, int kolicina, boolean prodaja) {
        this.id = id;
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;

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


}
