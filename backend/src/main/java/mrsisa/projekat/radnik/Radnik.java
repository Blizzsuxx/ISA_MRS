package mrsisa.projekat.radnik;

import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.poseta.Poseta;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "radnici")
@PrimaryKeyJoinColumn(name = "korisnik")
public abstract class Radnik extends Korisnik {

    @OneToMany(mappedBy = "radnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GodisnjiOdmor> godisnjiOdmori;

    public Radnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori, List<Poseta> posete) {
        super(username, password, firstName, lastName, email, birthday);
        this.godisnjiOdmori = godisnjiOdmori;
        this.posete = posete;
    }

    @OneToMany(mappedBy = "radnik", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
}
