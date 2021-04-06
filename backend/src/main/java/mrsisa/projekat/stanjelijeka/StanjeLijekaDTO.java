package mrsisa.projekat.stanjelijeka;

import mrsisa.projekat.lijek.Lijek;

import java.time.LocalDateTime;

public class StanjeLijekaDTO {
    private Long id;
    private Lijek lijek;
    private int kolicina;
    private boolean prodaja;
    private double cijena;
    private LocalDateTime datumIstekaCijene;

    public StanjeLijekaDTO(){

    }
    public StanjeLijekaDTO(StanjeLijeka stanjeLijeka){
        this.id = stanjeLijeka.getId();
        this.lijek = stanjeLijeka.getLijek();
        this.kolicina  =stanjeLijeka.getKolicina();
        this.prodaja = stanjeLijeka.isProdaja();
        this.cijena = stanjeLijeka.getCijena();
        this.datumIstekaCijene  = stanjeLijeka.getDatumIstekaCijene();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lijek getLijek() {
        return lijek;
    }

    public void setLijek(Lijek lijek) {
        this.lijek = lijek;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public boolean isProdaja() {
        return prodaja;
    }

    public void setProdaja(boolean prodaja) {
        this.prodaja = prodaja;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public LocalDateTime getDatumIstekaCijene() {
        return datumIstekaCijene;
    }

    public void setDatumIstekaCijene(LocalDateTime datumIstekaCijene) {
        this.datumIstekaCijene = datumIstekaCijene;
    }
}
