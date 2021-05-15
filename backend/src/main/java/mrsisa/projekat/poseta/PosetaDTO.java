package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PosetaDTO {

    private Long id;
    private Pacijent pacijent;
    private Radnik radnik;
    private String pocetak;
    private String kraj;
    private String opis;
    private Apoteka apoteka;
    private List<Lijek> prepisaniLekovi;
    private double cena;
    private int ocena;

    public List<Lijek> getPrepisaniLekovi() {
        return prepisaniLekovi;
    }

    public PosetaDTO(Long id, Radnik r, String pocetak, String kraj, String opis,  Apoteka apoteka, int ocena, double cena){
        this.id=id;
        this.radnik=r;
        this.pocetak=pocetak;
        this.kraj=kraj;
        this.opis=opis;
        this.apoteka=apoteka;
        this.ocena=ocena;
        this.cena=cena;

    }

    public void setPrepisaniLekovi(List<Lijek> prepisaniLekovi) {
        this.prepisaniLekovi = prepisaniLekovi;
    }
    public void setPrepisaniLekoviLista(List<Erecept> recept) {
        this.prepisaniLekovi=new ArrayList<>();
        for(Erecept e : recept){
            for(StanjeLijeka st : e.getPrepisaniLijekovi()){
                this.prepisaniLekovi.add(st.getLijek());
            }
        }

    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public String getPocetak() {
        return pocetak;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public PosetaDTO(Poseta p) {
        this.id = p.getId();
        this.pacijent = p.getPacijent();
        this.radnik = p.getRadnik();
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.pocetak = p.getPocetak().format(df);
        this.kraj = p.getKraj().format(df);
        this.opis = p.getOpis();
        this.apoteka = p.getApoteka();
    }
    public PosetaDTO(){}
}
