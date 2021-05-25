package mrsisa.projekat.dermatolog;

import javax.persistence.*;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.ocena.Ocena;
import mrsisa.projekat.poseta.Poseta;
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


    @OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RadnoVrijeme> radnaVremena;



    @ManyToMany(mappedBy = "dermatolozi")
    private List<Apoteka> apoteke;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Ocena> ocene;

    public Dermatolog() {
    }

    public Dermatolog(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        super(username, password, firstName, lastName, email, birthday);

    }

    public Dermatolog(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori) {
        super(username, password, firstName, lastName, email, birthday, godisnjiOdmori);

    }


    public Dermatolog(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori, List<Poseta> posete,Apoteka apoteka) {
        super(username, password, firstName, lastName, email, birthday, godisnjiOdmori, posete);

    }
    public List<RadnoVrijeme> getRadnaVremena() {
        return radnaVremena;
    }

    public void setRadnaVremena(List<RadnoVrijeme> radnaVremena) {
        this.radnaVremena = radnaVremena;
    }


    public Dermatolog(KorisnikDTO dummy) {
        super(dummy);
    }

    @Override
    public Apoteka orElse(Object o) {
        return null;
    }

    public List<Apoteka> getApoteke() {
        return apoteke;
    }

    public void setApoteke(List<Apoteka> apoteke) {
        this.apoteke = apoteke;
    }

    public List<Ocena> getOcene() {
        return ocene;
    }

    public void setOcene(List<Ocena> ocene) {
        this.ocene = ocene;
    }
}
