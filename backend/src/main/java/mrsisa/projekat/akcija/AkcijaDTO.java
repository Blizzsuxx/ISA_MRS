package mrsisa.projekat.akcija;

import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;

import java.time.LocalDate;

public class AkcijaDTO {

    private String opis;
    private StanjeLijekaDTO stanjeLijeka;
    private Long Id;
    private LocalDate datumVazenja;

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public StanjeLijekaDTO getStanjeLijeka() {
        return stanjeLijeka;
    }

    public void setStanjeLijeka(StanjeLijekaDTO stanjeLijeka) {
        this.stanjeLijeka = stanjeLijeka;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(LocalDate datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public AkcijaDTO(String opis, StanjeLijekaDTO stanjeLijeka, Long id, LocalDate datumVazenja) {
        this.opis = opis;
        this.stanjeLijeka = stanjeLijeka;
        this.Id = id;
        this.datumVazenja = datumVazenja;
    }

    public AkcijaDTO() {
    }

    public AkcijaDTO(Akcija a) {
        this.opis = a.getOpis();
        //this.stanjeLijeka = a.getStanjeLijeka();
        this.Id = a.getId();
        this.datumVazenja = LocalDate.now();
    }
}
