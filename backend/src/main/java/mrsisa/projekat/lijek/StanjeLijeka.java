package mrsisa.projekat.lijek;

public class StanjeLijeka {
    private int id;
    private Lijek lijek;
    private int kolicina;
    private boolean prodaja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public StanjeLijeka(Lijek lijek, int kolicina, boolean prodaja) {
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
    }

    public StanjeLijeka(int id, Lijek lijek, int kolicina, boolean prodaja) {
        this.id = id;
        this.lijek = lijek;
        this.kolicina = kolicina;
        this.prodaja = prodaja;
    }
    public StanjeLijeka(){

    }
}
