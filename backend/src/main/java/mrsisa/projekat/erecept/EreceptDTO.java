package mrsisa.projekat.erecept;

import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.poseta.Poseta;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import mrsisa.projekat.stanjelijeka.StanjeLijekaDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EreceptDTO {
    private long id;



    private String sifra;
    private Pacijent pacijent;
    private String datumIzdavanja;
    private List<StanjeLijeka> prepisaniLijekovi;
    private List<StanjeLijekaDTO> prepisaniLijekovi2;
    private Poseta poseta;
    private String nazivApoteke;


    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public String getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(String datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public List<StanjeLijeka> getPrepisaniLijekovi() {
        return prepisaniLijekovi;
    }

    public void setPrepisaniLijekovi(List<StanjeLijeka> prepisaniLijekovi) {
        this.prepisaniLijekovi = prepisaniLijekovi;
    }
    public String getNazivApoteke() {
        return nazivApoteke;
    }

    public void setNazivApoteke(String nazivApoteke) {
        this.nazivApoteke = nazivApoteke;
    }
    public Poseta getPoseta() {
        return poseta;
    }

    public void setPoseta(Poseta poseta) {
        this.poseta = poseta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<StanjeLijekaDTO> getPrepisaniLijekovi2() {
        return prepisaniLijekovi2;
    }

    public void setPrepisaniLijekovi2(List<StanjeLijekaDTO> prepisaniLijekovi2) {
        this.prepisaniLijekovi2 = prepisaniLijekovi2;
    }

    public EreceptDTO(long id, String sifra, Pacijent pacijent, String datumIzdavanja, List<StanjeLijeka> prepisaniLijekovi, Poseta poseta1) {
        this.id = id;
        this.sifra = sifra;
        this.pacijent = pacijent;
        this.datumIzdavanja = datumIzdavanja;
        this.prepisaniLijekovi = prepisaniLijekovi;
        this.poseta = poseta1;
    }
    public EreceptDTO(long id, String sifra, Pacijent pacijent, LocalDateTime datumIzdavanja1, List<StanjeLijeka> prepisaniLijekovi, Poseta poseta) {
        this.id = id;
        this.sifra = sifra;
        this.pacijent = pacijent;
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        this.datumIzdavanja = datumIzdavanja1.format(formater);
        this.prepisaniLijekovi = prepisaniLijekovi;
        this.poseta = poseta;
    }

    public EreceptDTO(Erecept er) {
        this.id = er.getId();
        this.sifra = er.getSifra();
        this.pacijent = er.getPacijent();
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        this.datumIzdavanja = er.getDatumIzdavanja().format(formater);
        this.prepisaniLijekovi = er.getPrepisaniLijekovi();
        this.poseta = poseta;
    }
    public EreceptDTO(){}
    public EreceptDTO(Erecept erecept, int a){
        this.sifra=erecept.getSifra();
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.datumIzdavanja= erecept.getDatumIzdavanja().format(formater);
        this.prepisaniLijekovi2=new ArrayList<>();

        for(StanjeLijeka s : erecept.getPrepisaniLijekovi()){
            if(s.getApoteka()!=null){
            this.nazivApoteke=s.getApoteka().getIme();}
            this.prepisaniLijekovi2.add(new StanjeLijekaDTO(s));
        }



    }
}
