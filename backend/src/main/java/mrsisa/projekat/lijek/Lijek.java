package mrsisa.projekat.lijek;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mrsisa.projekat.akcija.Akcija;
import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.ocena.Ocena;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lijek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sifra", nullable = false, unique = true)
    private String sifra;

    @Column(name="samo_recept", nullable = false)
    private boolean samoRecept;


    // za sada ovako
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ocena> ocene;
    @Column(name = "poeni", nullable = false)
    private int poeni;
    @Column(name = "preporuceni_unos", nullable = false)
    private String preporuceniUnos;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Dobavljac dobavljac;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Lijek> zamenskiLijekovi;

    public boolean isSamoRecept() {
        return samoRecept;
    }

    public void setSamoRecept(boolean samoRecept) {
        this.samoRecept = samoRecept;
    }

    public List<Lijek> getZamenskiLijekovi() {
        return zamenskiLijekovi;
    }

    public void setZamenskiLijekovi(List<Lijek> zamenskiLijekovi) {
        this.zamenskiLijekovi = zamenskiLijekovi;
    }

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

    public List<Ocena> getOcene() {
        return ocene;
    }

    public void setOcene(List<Ocena> ocene) {
        this.ocene = ocene;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getPreporuceniUnos() {
        return preporuceniUnos;
    }

    public void setPreporuceniUnos(String preporuceniUnos) {
        this.preporuceniUnos = preporuceniUnos;
    }

    public void setId(Long id) {
        this.id = id;
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
        //this.ocijena = ocijena;
        this.dobavljac = dobavljac;
    }

    public Lijek(LijekDTO dummy){
        this.sifra = dummy.getSifra();
        this.naziv = dummy.getNaziv();
        this.vrstaLijeka = dummy.getVrstaLijeka();
        this.oblikLijeka = dummy.getOblikLijeka();
        this.sastav = dummy.getSastav();
        this.proizvodjac = dummy.getProizvodjac();
        this.napomena = dummy.getNapomena();
        this.poeni = dummy.getPoeni();
        this.preporuceniUnos = dummy.getPreporuceniUnos();
        this.zamenskiLijekovi = new ArrayList<>();
    }


    public Lijek(Long id, String sifra, String naziv, String vrstaLijeka, String oblikLijeka, String sastav, String proizvodjac, String napomena, List<Ocena> ocene, int poeni, Dobavljac dobavljac, List<Lijek> zamenskiLijekovi) {
        this.id = id;
        this.sifra = sifra;
        this.naziv = naziv;
        this.vrstaLijeka = vrstaLijeka;
        this.oblikLijeka = oblikLijeka;
        this.sastav = sastav;
        this.proizvodjac = proizvodjac;
        this.napomena = napomena;
        this.ocene = ocene;
        this.poeni = poeni;
        this.dobavljac = dobavljac;
        this.zamenskiLijekovi = zamenskiLijekovi;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
