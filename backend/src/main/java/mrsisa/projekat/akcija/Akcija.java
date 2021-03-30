package mrsisa.projekat.akcija;

import javax.persistence.*;

import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

@Entity
public class Akcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "procenatPopusta", nullable = false)
    private int procenatPopusta;
    @Column(name = "opis", nullable = true)
    private String opis;
    @OneToOne(cascade = CascadeType.ALL)
    private StanjeLijeka stanje;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProcenatPopusta() {
        return procenatPopusta;
    }

    public void setProcenatPopusta(int procenatPopusta) {
        this.procenatPopusta = procenatPopusta;
    }


    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Akcija(int procenatPopusta, String opis) {
        super();
        this.procenatPopusta = procenatPopusta;

        this.opis = opis;
    }

    public Akcija() {

    }

}