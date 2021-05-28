package mrsisa.projekat.radnik;

public class RadnikDTO {
    private int id;
    private String ime, prezime, mejl;

    public RadnikDTO(int id, String ime, String prezime, String mejl) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.mejl = mejl;
    }
    public RadnikDTO(Radnik r) {
        this.id = r.getId();
        this.ime = r.getFirstName();
        this.prezime = r.getLastName();
        this.mejl = r.getEmail();
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
