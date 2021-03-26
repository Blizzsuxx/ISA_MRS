package mrsisa.projekat.ponuda;

import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.pacijent.Pacijent;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Ponuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nazivPonude", nullable = false)
    private String nazivPonude;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Dobavljac dobavljac;

    public Ponuda() {}

    public Ponuda(String nazivPonude, Dobavljac dobavljac) {
        this.nazivPonude = nazivPonude;
        this.dobavljac = dobavljac;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivPonude() {
        return nazivPonude;
    }

    public void setNazivPonude(String nazivPonude) {
        this.nazivPonude = nazivPonude;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }
}
