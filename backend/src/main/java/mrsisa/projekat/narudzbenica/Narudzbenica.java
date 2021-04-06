package mrsisa.projekat.narudzbenica;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Narudzbenica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Apoteka apoteka;

    @OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StanjeLijeka> lijekovi;
    private LocalDateTime rok;

}
