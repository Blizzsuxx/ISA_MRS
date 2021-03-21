package mrsisa.projekat.apoteka;


import mrsisa.projekat.lijek.StanjeLijeka;

import java.util.List;

public class Apoteka {
    private List<StanjeLijeka> lijekovi;
    private String ime;
    private int id;
    public Apoteka(){

    }
    public Apoteka(int id, String ime,List<StanjeLijeka> lijekovi){
        this.id = id;
        this.ime  = ime;
        this.lijekovi = lijekovi;
    }
    public Apoteka(String ime,List<StanjeLijeka> lijekovi){
        this.ime  =  ime;
        this.lijekovi = lijekovi;

    }

    public List<StanjeLijeka> getLijekovi() {
        return lijekovi;
    }

    public void setLijekovi(List<StanjeLijeka> lijekovi) {
        this.lijekovi = lijekovi;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
