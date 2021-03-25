package mrsisa.projekat.administratorApoteke;

import javax.persistence.*;

import mrsisa.projekat.narudzbenica.*;
import mrsisa.projekat.administrator.Administrator;
import mrsisa.projekat.apoteka.Apoteka;

@Entity
public class AdministratorApoteke extends Administrator {


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Apoteka apoteka;


}