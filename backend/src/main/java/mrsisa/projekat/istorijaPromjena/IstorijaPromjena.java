package mrsisa.projekat.istorijaPromjena;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class IstorijaPromjena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Apoteka apoteka;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public StanjeLijeka stanjeLijeka;

    @Column(name = "datumOd")
    private LocalDateTime datumOd;

    public IstorijaPromjena(Apoteka apoteka,StanjeLijeka stanjeLijeka){
        this.apoteka  = apoteka;
        this.stanjeLijeka  = stanjeLijeka;
    }
    public IstorijaPromjena(){

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

    public StanjeLijeka getStanjeLijeka() {
        return stanjeLijeka;
    }

    public void setStanjeLijeka(StanjeLijeka stanjeLijeka) {
        this.stanjeLijeka = stanjeLijeka;
    }

    public LocalDateTime getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDateTime datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDateTime getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDateTime datumDo) {
        this.datumDo = datumDo;
    }

    @Column(name = "datumDo")
    private LocalDateTime datumDo;



}
