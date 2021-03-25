package mrsisa.projekat.stanjelijeka;


import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.lijek.Lijek;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="stanje_lijeka")
public class StanjeLijeka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Lijek lijek;
    @Column(name = "kolicina", nullable = false)
    private int kolicina;
    @Column(name = "prodaja", nullable = false)
    private boolean prodaja;
    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;
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

    public StanjeLijeka(Lijek lijek, int kolicina, boolean prodaja) {
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
    }

    public StanjeLijeka(Long id, Lijek lijek, int kolicina, boolean prodaja) {
        this.id = id;
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
    }
    public StanjeLijeka(){

    }
}
