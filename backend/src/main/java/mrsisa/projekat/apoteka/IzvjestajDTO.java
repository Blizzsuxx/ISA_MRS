package mrsisa.projekat.apoteka;

import java.util.ArrayList;
import java.util.HashMap;

public class IzvjestajDTO {
    private double ocjenaApoteke;
    private HashMap<String,Double> ocjeneDermatologa;
    private HashMap<String,Double> ocjeneFarmaceuta;
    private HashMap<String,Integer> preglediGodine;
    private ArrayList<Integer> preglediPoMjesecima;
    private HashMap<String,Integer> lijekoviGodine;
    private ArrayList<Integer> lijekoviPoMjesecima;
    private HashMap<String,Integer> prihodiPeriod;


    public IzvjestajDTO(){
            this.ocjeneDermatologa =  new HashMap<>();
            this.ocjeneFarmaceuta =  new HashMap<>();
            this.preglediGodine  =  new HashMap<>();
            this.preglediPoMjesecima =  new ArrayList<>();
            this.lijekoviGodine =  new HashMap<>();
            this.lijekoviPoMjesecima =  new ArrayList<>();
            this.prihodiPeriod =  new HashMap<>();
    }

    public HashMap<String, Integer> getPrihodiPeriod() {
        return prihodiPeriod;
    }

    public void setPrihodiPeriod(HashMap<String, Integer> prihodiPeriod) {
        this.prihodiPeriod = prihodiPeriod;
    }

    public HashMap<String, Integer> getPreglediGodine() {
        return preglediGodine;
    }

    public void setPreglediGodine(HashMap<String, Integer> preglediGodine) {
        this.preglediGodine = preglediGodine;
    }

    public double getOcjenaApoteke() {
        return ocjenaApoteke;
    }

    public void setOcjenaApoteke(double ocjenaApoteke) {
        this.ocjenaApoteke = ocjenaApoteke;
    }

    public HashMap<String, Double> getOcjeneDermatologa() {
        return ocjeneDermatologa;
    }

    public void setOcjeneDermatologa(HashMap<String, Double> ocjeneDermatologa) {
        this.ocjeneDermatologa = ocjeneDermatologa;
    }

    public HashMap<String, Double> getOcjeneFarmaceuta() {
        return ocjeneFarmaceuta;
    }

    public void setOcjeneFarmaceuta(HashMap<String, Double> ocjeneFarmaceuta) {
        this.ocjeneFarmaceuta = ocjeneFarmaceuta;
    }

    public ArrayList<Integer> getPreglediPoMjesecima() {
        return preglediPoMjesecima;
    }

    public void setPreglediPoMjesecima(ArrayList<Integer> pregledi) {
        this.preglediPoMjesecima = pregledi;
    }

    public HashMap<String, Integer> getLijekoviGodine() {
        return lijekoviGodine;
    }

    public void setLijekoviGodine(HashMap<String, Integer> lijekoviGodine) {
        this.lijekoviGodine = lijekoviGodine;
    }

    public ArrayList<Integer> getLijekoviPoMjesecima() {
        return lijekoviPoMjesecima;
    }

    public void setLijekoviPoMjesecima(ArrayList<Integer> lijekoviPoMjesecima) {
        this.lijekoviPoMjesecima = lijekoviPoMjesecima;
    }
}
