package mrsisa.projekat.godisnjiodmor;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Radnik radnik;

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
}
