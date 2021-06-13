package mrsisa.projekat.radnik;

public class RadnikDTO {
    private int id;
    private String ime, prezime, mejl;

    public Boolean getPromenioSifru() {
        return promenioSifru;
    }

    public void setPromenioSifru(Boolean promenioSifru) {
        this.promenioSifru = promenioSifru;
    }

    private Boolean promenioSifru;

    public RadnikDTO(int id, String ime, String prezime, String mejl) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.mejl = mejl;
        this.promenioSifru = false;
    }
    public RadnikDTO(int id, String ime, String prezime, String mejl, Boolean promenioSifru) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.mejl = mejl;
        this.promenioSifru = promenioSifru;
    }
    public RadnikDTO(Radnik r) {
        this.id = r.getId();
        this.ime = r.getFirstName();
        this.prezime = r.getLastName();
        this.mejl = r.getEmail();
        this.promenioSifru = r.getPromenioSifru();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }
}
