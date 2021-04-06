package mrsisa.projekat.radnik;

import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.Korisnik;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "radnici")
@PrimaryKeyJoinColumn(name = "korisnik")
public abstract class Radnik extends Korisnik {

    @OneToMany(mappedBy = "radnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GodisnjiOdmor> godisnjiOdmori;

    public Radnik() {}

    public Radnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori) {
        super(username, password, firstName, lastName, email, birthday);
        this.godisnjiOdmori = godisnjiOdmori;
    }

    public Radnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        super(username, password, firstName, lastName, email, birthday);
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
