package mrsisa.projekat.narudzbenica;

import mrsisa.projekat.apoteka.Apoteka;

import javax.persistence.*;

@Entity
public class Narudzbenica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;
}
