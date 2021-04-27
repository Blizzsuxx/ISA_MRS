package mrsisa.projekat.farmaceut;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.radnik.Radnik;

import javax.persistence.*;

@Entity
@Table(name = "farmaceuti")
@PrimaryKeyJoinColumn(name = "radnik")
public class Farmaceut extends Radnik {

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    public Farmaceut() {}



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
}
