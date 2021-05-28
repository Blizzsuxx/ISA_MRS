package mrsisa.projekat.radnik;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.slobodanTermin.SlobodanTermin;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "radnici")
@PrimaryKeyJoinColumn(name = "korisnik")
public abstract class Radnik extends Korisnik {

    @OneToMany(mappedBy = "radnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GodisnjiOdmor> godisnjiOdmori;

    @Column(name = "promenioSifru")
    private Boolean promenioSifru = false;

    public Radnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori, List<Poseta> posete) {
        super(username, password, firstName, lastName, email, birthday);
        this.godisnjiOdmori = godisnjiOdmori;
        this.posete = posete;
    }
    @OneToMany(mappedBy = "radnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SlobodanTermin> slobodniTermini;

    public List<SlobodanTermin> getSlobodniTermini() {
        return slobodniTermini;
    }

    public void setSlobodniTermini(List<SlobodanTermin> slobodniTermini) {
        this.slobodniTermini = slobodniTermini;
    }

    @OneToMany(mappedBy = "radnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Poseta> posete;

    public Radnik() {}

    public List<Poseta> getPosete() {
        return posete;
    }

    public void setPosete(List<Poseta> posete) {
        this.posete = posete;
    }

    public Radnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori) {
        super(username, password, firstName, lastName, email, birthday);
        this.godisnjiOdmori = godisnjiOdmori;
    }

    public Radnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        super(username, password, firstName, lastName, email, birthday);
    }

    public Radnik(KorisnikDTO dummy) {
        super(dummy);
    }

    public Radnik(List<GodisnjiOdmor> godisnjiOdmori) {
        this.godisnjiOdmori = godisnjiOdmori;
    }

    public List<GodisnjiOdmor> getGodisnjiOdmori() {
        return godisnjiOdmori;
    }

    public void setGodisnjiOdmori(List<GodisnjiOdmor> godisnjiOdmori) {
        this.godisnjiOdmori = godisnjiOdmori;
    }

    public Boolean getPromenioSifru() {
        return promenioSifru;
    }

    public void setPromenioSifru(Boolean promenioSifru) {
        this.promenioSifru = promenioSifru;
    }
}
