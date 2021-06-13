package mrsisa.projekat.pacijent;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.adresa.AdresaDTO;
import mrsisa.projekat.lijek.Lijek;
import mrsisa.projekat.lijek.LijekDTO;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PacijentDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime birthday;
    private List<LijekDTO> alergije;
    private AdresaDTO adresa;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public List<LijekDTO> getAlergije() {
        return alergije;
    }

    public void setAlergije(List<LijekDTO> alergije) {
        this.alergije = alergije;
    }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }

    public PacijentDTO(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, List<LijekDTO> alergije, AdresaDTO adresa) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.alergije = alergije;
        this.adresa = adresa;
    }

    public PacijentDTO(Pacijent p) {
        this.username = p.getUsername();
        this.password = p.getPassword();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email = p.getEmail();
        this.birthday = p.getBirthday();
        this.alergije = new ArrayList<LijekDTO>();
        this.adresa=new AdresaDTO(p.getAdresa());
        for(Lijek l:p.getAlergije()){
            this.alergije.add(new LijekDTO(l));
        }
    }
}
