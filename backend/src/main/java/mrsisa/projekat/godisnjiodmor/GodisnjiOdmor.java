package mrsisa.projekat.godisnjiodmor;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class GodisnjiOdmor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datumOd", nullable = false)
    private LocalDateTime datumOd;

    @Column(name = "datumDo")
    private LocalDateTime datumDo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Radnik radnik;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    @Column(name = "odobren", nullable = true)
    private Boolean odobren;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOdobren() {
        return odobren;
    }

    public void setOdobren(Boolean odobren) {
        this.odobren = odobren;
    }

    public GodisnjiOdmor() {}

    public GodisnjiOdmor(LocalDateTime datumOd, LocalDateTime datumDo, Radnik radnik) {
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.radnik = radnik;
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

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }
}
