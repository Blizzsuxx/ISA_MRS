package mrsisa.projekat.lijek;

import mrsisa.projekat.akcija.Akcija;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lijek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;
    @Column(name = "vrstaLijeka", nullable = false)
    private String vrstaLijeka;
    @Column(name = "oblikLijeka", nullable = false)
    private String oblikLijeka;
    @Column(name = "sastav", nullable = false)
    private String sastav;
    @Column(name = "proizvodjac", nullable = false)
    private String proizvodjac;
    //private RezimIzdavanja rezimIzdavanja;
    @Column(name = "napomena", nullable = false)
    private String napomena;
    @Column(name = "ocijena", nullable = false)
    private double ocijena;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Dobavljac dobavljac;



    public Long getId() {
        return id;
    }

    public void setSifra(Long sifra) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrstaLijeka() {
        return vrstaLijeka;
    }

    public void setVrstaLijeka(String vrstaLijeka) {
        this.vrstaLijeka = vrstaLijeka;
    }

    public String getOblikLijeka() {
        return oblikLijeka;
    }

    public void setOblikLijeka(String oblikLijeka) {
        this.oblikLijeka = oblikLijeka;
    }

    public String getSastav() {
        return sastav;
    }

    public void setSastav(String sastav) {
        this.sastav = sastav;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public double getOcijena() {
        return ocijena;
    }

    public void setOcijena(double ocijena) {
        this.ocijena = ocijena;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public Lijek() {

    }

    public Lijek(Long id, String naziv, String vrstaLijeka, String oblikLijeka, String sastav, String proizvodjac, String napomena) {
        this.id = id;
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.oblikLijeka = oblikLijeka;
        this.sastav = sastav;
        this.proizvodjac = proizvodjac;
        this.napomena = napomena;
    }

    public Lijek(String naziv, String vrstaLijeka, String oblikLijeka, String sastav, String proizvodjac, String napomena) {
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.oblikLijeka = oblikLijeka;
        this.sastav = sastav;
        this.proizvodjac = proizvodjac;
        this.napomena = napomena;
    }

    public Lijek(String naziv, String vrstaLijeka, String oblikLijeka, String sastav, String proizvodjac, String napomena, double ocijena, Dobavljac dobavljac) {
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.oblikLijeka = oblikLijeka;
        this.sastav = sastav;
        this.proizvodjac = proizvodjac;
        this.napomena = napomena;
        this.ocijena = ocijena;
        this.dobavljac = dobavljac;
    }
}
