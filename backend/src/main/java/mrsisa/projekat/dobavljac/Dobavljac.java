package mrsisa.projekat.dobavljac;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.KorisnikDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.ponuda.Ponuda;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dobavljaci")
@PrimaryKeyJoinColumn(name = "radnik")
public class Dobavljac extends Radnik {
    @OneToMany(mappedBy = "dobavljac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lijek> lijekoviNaStanju;
    @OneToMany(mappedBy = "dobavljac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ponuda> ponude;

    public Dobavljac() {}

    public Dobavljac(List<Lijek> lijekoviNaStanju, List<Ponuda> ponude) {
        this.lijekoviNaStanju = lijekoviNaStanju;
        this.ponude = ponude;
    }

    public Dobavljac(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        super(username, password, firstName, lastName, email, birthday);
    }

    public Dobavljac(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<Lijek> lijekoviNaStanju, List<Ponuda> ponude) {
        super(username, password, firstName, lastName, email, birthday);
        this.lijekoviNaStanju = lijekoviNaStanju;
        this.ponude = ponude;
    }

    public Dobavljac(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori) {
        super(username, password, firstName, lastName, email, birthday, godisnjiOdmori);
    }

    public Dobavljac(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<GodisnjiOdmor> godisnjiOdmori, List<Lijek> lijekoviNaStanju, List<Ponuda> ponude) {
        super(username, password, firstName, lastName, email, birthday, godisnjiOdmori);
        this.lijekoviNaStanju = lijekoviNaStanju;
        this.ponude = ponude;
    }

    public Dobavljac(KorisnikDTO dummy) {
        super(dummy);
    }

    @Override
    public Apoteka orElse(Object o) {
        return null;
    }

    public List<Lijek> getLijekoviNaStanju() {
        return lijekoviNaStanju;
    }

    public void setLijekoviNaStanju(List<Lijek> lijekoviNaStanju) {
        this.lijekoviNaStanju = lijekoviNaStanju;
    }

    public List<Ponuda> getPonude() {
        return ponude;
    }

    public void setPonude(List<Ponuda> ponude) {
        this.ponude = ponude;
    }
}
