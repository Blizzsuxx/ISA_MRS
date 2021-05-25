package mrsisa.projekat.ocena;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.dermatolog.Dermatolog;
import mrsisa.projekat.farmaceut.Farmaceut;
import mrsisa.projekat.lijek.Lijek;

public class OcenaDTO {
    private String id; //D+id ili L+id...
    private String naziv;
    private double prosecnaOcena; //sve ukupno
    private int ocenaKorisnika; //nova
    private int staraOcenaKorisnika; //stara
    private String idKorisnika;

    public OcenaDTO(Dermatolog d) {
        this.id="D"+d.getId();
        this.naziv=d.getFirstName()+" "+d.getLastName();
        this.prosecnaOcena=0;
        this.ocenaKorisnika=0;
        this.staraOcenaKorisnika=0;
    }
    public OcenaDTO(Farmaceut d) {
        this.id="F"+d.getId();
        this.naziv=d.getFirstName()+" "+d.getLastName();
        this.prosecnaOcena=0;
        this.ocenaKorisnika=0;
        this.staraOcenaKorisnika=0;
    }
    public OcenaDTO(Apoteka d) {
        this.id="A"+d.getId();
        this.naziv=d.getIme();
        this.prosecnaOcena=0;
        this.ocenaKorisnika=0;
        this.staraOcenaKorisnika=0;
    }
    public OcenaDTO(Lijek d) {
        this.id="L"+d.getId();
        this.naziv=d.getNaziv();
        this.prosecnaOcena=0;
        this.ocenaKorisnika=0;
        this.staraOcenaKorisnika=0;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public int getOcenaKorisnika() {
        return ocenaKorisnika;
    }

    public void setOcenaKorisnika(int ocenaKorisnika) {
        this.ocenaKorisnika = ocenaKorisnika;
    }

    public int getStaraOcenaKorisnika() {
        return staraOcenaKorisnika;
    }

    public void setStaraOcenaKorisnika(int staraOcenaKorisnika) {
        this.staraOcenaKorisnika = staraOcenaKorisnika;
    }

    public String getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(String idKorisnika) {
        this.idKorisnika = idKorisnika;
    }
    public OcenaDTO(Ocena o, Object ocenjeno){

        if(ocenjeno instanceof Dermatolog){
        this.id="D"+((Dermatolog) ocenjeno).getId();
        this.naziv=((Dermatolog)ocenjeno).getFirstName()+" "+((Dermatolog)ocenjeno).getLastName();
        }
        else if(ocenjeno instanceof Farmaceut){
            this.id="F"+((Farmaceut) ocenjeno).getId();
            this.naziv=((Farmaceut)ocenjeno).getFirstName()+" "+((Farmaceut)ocenjeno).getLastName();
        } else if(ocenjeno instanceof Lijek){
            this.id="L"+((Lijek) ocenjeno).getId();
            this.naziv=((Lijek)ocenjeno).getNaziv();
        }else {
            this.id="A"+((Apoteka) ocenjeno).getId();
            this.naziv=((Apoteka)ocenjeno).getIme();
        }
        this.idKorisnika=o.getPacijent().getId()+"";
        this.ocenaKorisnika=0;//nova
        this.staraOcenaKorisnika=o.getOcena();



    }

    public OcenaDTO(String id, String naziv, double prosecnaOcena, int ocenaKorisnika, int staraOcenaKorisnika, String idKorisnika) {
        this.id = id;
        this.naziv = naziv;
        this.prosecnaOcena = prosecnaOcena;
        this.ocenaKorisnika = ocenaKorisnika;
        this.staraOcenaKorisnika = staraOcenaKorisnika;
        this.idKorisnika = idKorisnika;
    }

    public OcenaDTO() {
    }
}
