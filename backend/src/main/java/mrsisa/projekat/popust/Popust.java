package mrsisa.projekat.popust;

import javax.persistence.*;

@Entity
public class Popust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "popust_regular")
    private int popustRegular;  // u procentima
    @Column(name = "od_regular")
    private int odRegular;
    @Column(name = "do_regular")
    private int doRegular;
    @Column(name = "popust_silver")
    private int popustSilver;  // u procentima
    @Column(name = "od_silver")
    private int odSilver;
    @Column(name = "do_silver")
    private int doSilver;
    @Column(name = "popust_gold")
    private int popustGold;  // u procentima
    @Column(name = "od_gold")
    private int odGold;
    @Column(name = "do_gold")
    private int doGold;
    @Column(name = "broj_poena_pregleda")
    private int brojPoenaPregleda;
    @Column(name = "broj_poena_savetovanja")
    private int brojPoenaSavetovanja;
    @Column(name = "odobren")
    private boolean odobren;

    public Popust(){

    }

    public Popust(int popustRegular, int odRegular, int doRegular, int popustSilver, int odSilver, int doSilver, int popustGold, int odGold, int doGold,
                  int brojPoenaPregleda, int brojPoenaSavetovanja, boolean odobren) {
        this.popustRegular = popustRegular;
        this.odRegular = odRegular;
        this.doRegular = doRegular;
        this.popustSilver = popustSilver;
        this.odSilver = odSilver;
        this.doSilver = doSilver;
        this.popustGold = popustGold;
        this.odGold = odGold;
        this.doGold = doGold;
        this.brojPoenaPregleda = brojPoenaPregleda;
        this.brojPoenaSavetovanja = brojPoenaSavetovanja;
        this.odobren = odobren;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getPopustRegular() {
        return popustRegular;
    }

    public void setPopustRegular(int popustRegular) {
        this.popustRegular = popustRegular;
    }

    public int getOdRegular() {
        return odRegular;
    }

    public void setOdRegular(int odRegular) {
        this.odRegular = odRegular;
    }

    public int getDoRegular() {
        return doRegular;
    }

    public void setDoRegular(int doRegular) {
        this.doRegular = doRegular;
    }

    public int getPopustSilver() {
        return popustSilver;
    }

    public void setPopustSilver(int popustSilver) {
        this.popustSilver = popustSilver;
    }

    public int getOdSilver() {
        return odSilver;
    }

    public void setOdSilver(int odSilver) {
        this.odSilver = odSilver;
    }

    public int getDoSilver() {
        return doSilver;
    }

    public void setDoSilver(int doSilver) {
        this.doSilver = doSilver;
    }

    public int getPopustGold() {
        return popustGold;
    }

    public void setPopustGold(int popustGold) {
        this.popustGold = popustGold;
    }

    public int getOdGold() {
        return odGold;
    }

    public void setOdGold(int odGold) {
        this.odGold = odGold;
    }

    public int getDoGold() {
        return doGold;
    }

    public void setDoGold(int doGold) {
        this.doGold = doGold;
    }

    public int getBrojPoenaPregleda() {
        return brojPoenaPregleda;
    }

    public void setBrojPoenaPregleda(int brojPoenaPregleda) {
        this.brojPoenaPregleda = brojPoenaPregleda;
    }

    public int getBrojPoenaSavetovanja() {
        return brojPoenaSavetovanja;
    }

    public void setBrojPoenaSavetovanja(int brojPoenaSavetovanja) {
        this.brojPoenaSavetovanja = brojPoenaSavetovanja;
    }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
