package mrsisa.projekat.radnoVrijeme;


import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;

import javax.persistence.*;

@Entity
public class RadnoVrijeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Apoteka apoteka;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Dermatolog dermatolog;


}
