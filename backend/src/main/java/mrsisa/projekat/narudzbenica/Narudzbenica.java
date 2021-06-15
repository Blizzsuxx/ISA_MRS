package mrsisa.projekat.narudzbenica;

import mrsisa.projekat.administratorApoteke.AdministratorApoteke;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.ponuda.Ponuda;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Narudzbenica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version;


    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    @OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> lijekovi;


    @OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ponuda> ponude;

    @Column(name="rok", nullable = false)
    private LocalDateTime rok;


    @Column(name="zavrsena",nullable = false)
    private boolean zavrsena;

    @Column(name="prihvacena",nullable=false)
    private boolean prihvacena;

    @OneToOne(fetch = FetchType.LAZY)
    public AdministratorApoteke administratorApoteke;

    public Narudzbenica(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public List<StanjeLijeka> getLijekovi() {
        return lijekovi;
    }

    public void setLijekovi(List<StanjeLijeka> lijekovi) {
        this.lijekovi = lijekovi;
    }

    public LocalDateTime getRok() {
        return rok;
    }

    public void setRok(LocalDateTime rok) {
        this.rok = rok;
    }

    public boolean isZavrsena() {
        return zavrsena;
    }

    public void setZavrsena(boolean zavrsena) {
        this.zavrsena = zavrsena;
    }

    public List<Ponuda> getPonude() {
        return ponude;
    }

    public void setPonude(List<Ponuda> ponude) {
        this.ponude = ponude;
    }

    public boolean isPrihvacena() {
        return prihvacena;
    }

    public void setPrihvacena(boolean prihvacena) {
        this.prihvacena = prihvacena;
    }

    public AdministratorApoteke getAdministratorApoteke() {
        return administratorApoteke;
    }

    public void setAdministratorApoteke(AdministratorApoteke administratorApoteke) {
        this.administratorApoteke = administratorApoteke;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
