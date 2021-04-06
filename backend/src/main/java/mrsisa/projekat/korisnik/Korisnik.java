package mrsisa.projekat.korisnik;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "korisnici")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class  Korisnik {

    @Id
    @SequenceGenerator(name = "korisnik_seq", sequenceName = "korisnik_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "korisnik_seq")
    private Integer id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "birthday", nullable = false)
    private LocalDateTime birthday;

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Korisnik() {}

    public Korisnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }

    public Korisnik(KorisnikDTO dummy){
        this.username = dummy.getKorisnickoIme();
        this.password = dummy.getSifra();
        this.firstName = dummy.getIme();
        this.lastName = dummy.getPrezime();
        this.email = dummy.getEmail();
        this.birthday = LocalDateTime.parse(dummy.getRodjendan(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

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

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
