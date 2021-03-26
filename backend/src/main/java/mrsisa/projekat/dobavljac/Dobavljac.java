package mrsisa.projekat.dobavljac;

import mrsisa.projekat.korisnik.Korisnik;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.ponuda.Ponuda;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
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
