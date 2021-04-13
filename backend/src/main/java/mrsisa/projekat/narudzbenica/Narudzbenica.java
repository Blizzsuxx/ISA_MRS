package mrsisa.projekat.narudzbenica;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Narudzbenica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    @OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> lijekovi;
    private LocalDateTime rok;

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
}
