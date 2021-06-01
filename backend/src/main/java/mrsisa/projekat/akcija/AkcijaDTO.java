package mrsisa.projekat.akcija;

import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AkcijaDTO {

    private String opis;
    private StanjeLijekaDTO stanjeLijeka;
    private Long Id;
    private String datumVazenja;
    private String datumOd;
    private String datumDo;
    private int procenatPopusta;

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

    public String getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(String datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public AkcijaDTO(String opis, StanjeLijekaDTO stanjeLijeka, Long id, LocalDate datumVazenja) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.opis = opis;
        this.stanjeLijeka = stanjeLijeka;
        this.Id = id;
        this.datumVazenja = datumVazenja.format(dtf);
    }

    public AkcijaDTO() {
    }

    public AkcijaDTO(Akcija a, StanjeLijeka s) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.opis = a.getOpis();
        this.stanjeLijeka = new StanjeLijekaDTO(s);
        this.Id = a.getId();
        this.datumVazenja = LocalDate.now().format(dtf);
    }

    public AkcijaDTO(Akcija akcija){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.Id = akcija.getId();
        this.datumDo = akcija.getDatumDo().format(dtf);
        this.datumOd = akcija.getDatumOd().format(dtf);
        this.procenatPopusta  = akcija.getProcenatPopusta();
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public int getProcenatPopusta() {
        return procenatPopusta;
    }

    public void setProcenatPopusta(int procenatPopusta) {
        this.procenatPopusta = procenatPopusta;
    }
}
