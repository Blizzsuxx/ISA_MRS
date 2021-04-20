package mrsisa.projekat.dermatolog;

import javax.persistence.*;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.radnoVrijeme.RadnoVrijeme;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dermatolozi")
@PrimaryKeyJoinColumn(name = "radnik")
public class Dermatolog extends Radnik{
    @Column(name = "iq")
    private String iq; // atribut koji sluzi da bi istestirali validnost baze

    @OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RadnoVrijeme> radnaVremena;

    @OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SlobodanTermin> slobodniTermini;

    public Dermatolog() {
    }

    public Dermatolog(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String iq) {
        super(username, password, firstName, lastName, email, birthday);
        this.iq = iq;
    }

    public Dermatolog(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori, String iq) {
        super(username, password, firstName, lastName, email, birthday, godisnjiOdmori);
        this.iq = iq;
    }

    public List<RadnoVrijeme> getRadnaVremena() {
        return radnaVremena;
    }

    public void setRadnaVremena(List<RadnoVrijeme> radnaVremena) {
        this.radnaVremena = radnaVremena;
    }

    public List<SlobodanTermin> getSlobodniTermini() {
        return slobodniTermini;
    }

    public void setSlobodniTermini(List<SlobodanTermin> slobodniTermini) {
        this.slobodniTermini = slobodniTermini;
    }

    public Dermatolog(KorisnikDTO dummy) {
        super(dummy);
    }

    @Override
    public Apoteka orElse(Object o) {
        return null;
    }

    public Dermatolog(String iq) {
        this.iq = iq;
    }

    public String getIq() {
        return iq;
    }

    public void setIq(String iq) {
        this.iq = iq;
    }
}
