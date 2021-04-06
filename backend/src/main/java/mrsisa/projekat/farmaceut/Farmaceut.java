package mrsisa.projekat.farmaceut;

import mrsisa.projekat.radnik.Radnik;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "farmaceuti")
@PrimaryKeyJoinColumn(name = "radnik")
public class Farmaceut extends Radnik {

    public Farmaceut() {}
}
