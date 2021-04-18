package mrsisa.projekat.dermatolog;

import javax.persistence.*;

import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.radnoVrijeme.RadnoVrijeme;
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

    public Dermatolog(KorisnikDTO dummy) {
        super(dummy);
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
