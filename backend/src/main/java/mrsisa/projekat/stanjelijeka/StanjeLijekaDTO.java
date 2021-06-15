package mrsisa.projekat.stanjelijeka;

import mrsisa.projekat.akcija.AkcijaDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StanjeLijekaDTO {
    private Long id;
    private LijekDTO lijek;
    private int kolicina;
    private boolean prodaja;
    private double cijena;
    private String datumIstekaCijene;
    private String imeApoteke;
    private int zatrazen;
    private String zatrazenDatum;
    private AkcijaDTO akcija;


    public StanjeLijekaDTO(){

    }

    public StanjeLijekaDTO(Long id, LijekDTO lijek, int kolicina, boolean prodaja, double cijena, String datumIstekaCijene, String imeApoteke) {
        this.id = id;
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
        this.cijena = cijena;
        this.datumIstekaCijene = datumIstekaCijene;
        this.imeApoteke = imeApoteke;
    }

    public StanjeLijekaDTO(StanjeLijeka stanjeLijeka){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.id = stanjeLijeka.getId();
        this.lijek = new LijekDTO(stanjeLijeka.getLijek(),1);
        this.kolicina  =stanjeLijeka.getKolicina();
        this.prodaja = stanjeLijeka.isProdaja();
        this.cijena = stanjeLijeka.getCijena();
        this.datumIstekaCijene  = stanjeLijeka.getDatumIstekaCijene().format(format);
        this.zatrazen = stanjeLijeka.getZatrazen();
        if (stanjeLijeka.getZatrazenDatum() != null)
        this.zatrazenDatum =  stanjeLijeka.getZatrazenDatum().format(format);
        if (stanjeLijeka.getApoteka() != null)
        this.imeApoteke = stanjeLijeka.getApoteka().getIme();
    }

    public String getImeApoteke() {
        return imeApoteke;
    }

    public void setImeApoteke(String imeApoteke) {
        this.imeApoteke = imeApoteke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LijekDTO getLijek() {
        return lijek;
    }

    public void setLijek(LijekDTO lijek) {
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

    public String getDatumIstekaCijene() {
        return datumIstekaCijene;
    }

    public void setDatumIstekaCijene(String datumIstekaCijene) {
        this.datumIstekaCijene = datumIstekaCijene;
    }

    public int getZatrazen() {
        return zatrazen;
    }

    public void setZatrazen(int zatrazen) {
        this.zatrazen = zatrazen;
    }

    public String getZatrazenDatum() {
        return zatrazenDatum;
    }

    public void setZatrazenDatum(String zatrazenDatum) {
        this.zatrazenDatum = zatrazenDatum;
    }

    public AkcijaDTO getAkcija() {
        return akcija;
    }

    public void setAkcija(AkcijaDTO akcija) {
        this.akcija = akcija;
    }
}
