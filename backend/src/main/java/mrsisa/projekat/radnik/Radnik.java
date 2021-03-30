package mrsisa.projekat.radnik;

import mrsisa.projekat.godisnjiodmor.GodisnjiOdmor;
import mrsisa.projekat.korisnik.Korisnik;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Radnik extends Korisnik {

    @OneToMany(mappedBy = "radnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GodisnjiOdmor> godisnjiOdmori;

    public Radnik() {}

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