package mrsisa.projekat.adresa;


public class AdresaDTO {

    public String Mesto;
    public String ptt;
    public String ulica;
    public String broj;
    public double gDuzina;
    public double gSirina;

    public String getMesto() {
        return Mesto;
    }

    public void setMesto(String mesto) {
        Mesto = mesto;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public double getgDuzina() {
        return gDuzina;
    }

    public void setgDuzina(double gDuzina) {
        this.gDuzina = gDuzina;
    }

    public double getgSirina() {
        return gSirina;
    }

    public void setgSirina(double gSirina) {
        this.gSirina = gSirina;
    }

    public AdresaDTO(String mesto, String ptt, String ulica, String broj, double gDuzina, double gSirina) {
        Mesto = mesto;
        this.ptt = ptt;
        this.ulica = ulica;
        this.broj = broj;
        this.gDuzina = gDuzina;
        this.gSirina = gSirina;
    }
    public AdresaDTO(Adresa a) {
        Mesto = a.getMesto();
        this.ptt = a.getPtt();
        this.ulica = a.getUlica();
        this.broj = a.getBroj();
        this.gDuzina = a.getgDuzina();
        this.gSirina = a.getgSirina();
    }
}
