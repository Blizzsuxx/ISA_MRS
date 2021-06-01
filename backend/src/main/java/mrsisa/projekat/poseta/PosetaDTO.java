package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.apoteka.ApotekaDTO;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.erecept.Erecept;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;
import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.pacijent.PacijentDTO;
import mrsisa.projekat.radnik.Radnik;
import mrsisa.projekat.radnik.RadnikDTO;
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
    private PacijentDTO pacijent;
    private RadnikDTO radnik;
    private String pocetak;
    private String kraj;
    private String opis;
    private ApotekaDTO apoteka;
    private List<LijekDTO> prepisaniLekovi;
    private double cena;
    private int ocena;

    public PosetaDTO(Poseta p, Dermatolog d, Apoteka a) {
        this.id = p.getId();
        this.pacijent = null;
        this.radnik = new RadnikDTO(d);
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.pocetak = p.getPocetak().format(df);
        this.kraj = p.getKraj().format(df);
        this.opis = p.getOpis();
        this.apoteka = new ApotekaDTO(a,1); //ovde mi ne treba adresa pa cu namestiti nov konstruktor
        this.prepisaniLekovi=new ArrayList<>();
    }

    public List<LijekDTO> getPrepisaniLekovi() {
        return prepisaniLekovi;
    }

    public PosetaDTO(Long id, Radnik r, String pocetak, String kraj, String opis,  Apoteka apoteka, int ocena, double cena){
        this.id=id;
        this.radnik=new RadnikDTO(r);
        this.pocetak=pocetak;
        this.kraj=kraj;
        this.opis=opis;
        this.apoteka=new ApotekaDTO(apoteka);
        this.ocena=ocena;
        this.cena=cena;

    }

    public void setPrepisaniLekovi(List<LijekDTO> prepisaniLekovi) {
        this.prepisaniLekovi = prepisaniLekovi;
    }
    public void setPrepisaniLekoviLista(List<Erecept> recept) {
        this.prepisaniLekovi=new ArrayList<>();
        for(Erecept e : recept){
            for(StanjeLijeka st : e.getPrepisaniLijekovi()){
                this.prepisaniLekovi.add(new LijekDTO(st.getLijek()));
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

    public PacijentDTO getPacijent() {
        return pacijent;
    }

    public void setPacijent(PacijentDTO pacijent) {
        this.pacijent = pacijent;
    }

    public RadnikDTO getRadnik() {
        return radnik;
    }

    public void setRadnik(RadnikDTO radnik) {
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

    public ApotekaDTO getApoteka() {
        return apoteka;
    }

    public void setApoteka(ApotekaDTO apoteka) {
        this.apoteka = apoteka;
    }

    public PosetaDTO(Poseta p) {
        this.id = p.getId();
        this.pacijent = new PacijentDTO(p.getPacijent());
        this.radnik = new RadnikDTO(p.getRadnik());
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.pocetak = p.getPocetak().format(df);
        this.kraj = p.getKraj().format(df);
        this.opis = p.getOpis();
        this.apoteka = new ApotekaDTO(p.getApoteka());
        this.prepisaniLekovi=new ArrayList<>();
        if(p.getErecepti()!=null){
            for(Erecept e : p.getErecepti()){
                for(StanjeLijeka s :e.getPrepisaniLijekovi()){
                    this.prepisaniLekovi.add(new LijekDTO(s.getLijek()));
                }
            }
        }
    }
    public PosetaDTO(){}
}
