package mrsisa.projekat.apoteka;

import java.util.ArrayList;
import java.util.HashMap;

public class IzvjestajDTO {
    private double ocjenaApoteke;
    private HashMap<String,Double> ocjeneDermatologa;
    private HashMap<String,Double> ocjeneFarmaceuta;
    private ArrayList<Integer> preglediPoMjesecima;


    public IzvjestajDTO(){
            this.ocjeneDermatologa =  new HashMap<>();
            this.ocjeneFarmaceuta =  new HashMap<>();
            this.preglediPoMjesecima =  new ArrayList<>();
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
}
