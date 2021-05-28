package mrsisa.projekat.ponuda;

import mrsisa.projekat.dobavljac.Dobavljac;
import mrsisa.projekat.narudzbenica.Narudzbenica;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Dobavljac dobavljac;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Narudzbenica narudzbenica;

    @Column(name="status",nullable = false)
    private int status;

    @Column(name="cijenaPonude",nullable=false)
    private double cijenaPonude;

    public Ponuda() {}

    public Ponuda(String nazivPonude, Dobavljac dobavljac) {
        this.nazivPonude = nazivPonude;
        this.dobavljac = dobavljac;
    }

    public Ponuda(PonudaDTO ponudaDTO){
        this.nazivPonude = ponudaDTO.getNazivPonude();
        this.status = ponudaDTO.getStatus();
        this.cijenaPonude = ponudaDTO.getCijenaPonude();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public double getCijenaPonude() {
        return cijenaPonude;
    }

    public void setCijenaPonude(double cijenaPonude) {
        this.cijenaPonude = cijenaPonude;
    }
}
