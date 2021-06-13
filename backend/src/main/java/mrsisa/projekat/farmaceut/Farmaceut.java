package mrsisa.projekat.farmaceut;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.ocena.Ocena;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "farmaceuti")
@PrimaryKeyJoinColumn(name = "radnik")
public class Farmaceut extends Radnik {

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    public Farmaceut() {}

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Ocena> ocene;

    @Override
    public Apoteka orElse(Object o) {
        return null;
    }


    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public List<Ocena> getOcene() {
        return ocene;
    }

    public void setOcene(List<Ocena> ocene) {
        this.ocene = ocene;
    }
}
