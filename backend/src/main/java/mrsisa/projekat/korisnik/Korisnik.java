package mrsisa.projekat.korisnik;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mrsisa.projekat.uloga.Uloga;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "korisnici")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class  Korisnik implements UserDetails {
    @Version
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version;

    @Id
   // @SequenceGenerator(name = "korisnik_seq", sequenceName = "korisnik_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
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

    @Column(name = "role")
    private String role;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="lastPasswordResetDate")
    private Timestamp lastPasswordResetDate;

    @Column(name="potvrda_email")
    private boolean potvrdaEmail;

    @Column(name="prijavljen")
    private boolean prijavljen;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "korisnik_uloga",
            joinColumns = @JoinColumn(name = "korisnik_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "uloga_id", referencedColumnName = "id"))
    private List<Uloga> uloge;

    @Column(name = "ulica", nullable = false)
    private String ulica;
    @Column(name = "broj", nullable = false)
    private String broj;
    @Column(name = "mesto", nullable = false)
    private String mesto;
    @Column(name = "ptt", nullable = false)
    private String ptt;
    @Column(name = "drzava", nullable = false)
    private String drzava;
    @Column(name = "broj_telefona", nullable = false)
    private String brojTelefona;

    public Korisnik() {}

    public Korisnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }

    public Korisnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, boolean enabled, List<Uloga> uloge) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.enabled = enabled;
        this.uloge = uloge;
    }

    public Korisnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String role, boolean enabled, List<Uloga> uloge) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.enabled = enabled;
        this.uloge = uloge;
    }

    public Korisnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String role, boolean enabled, Timestamp lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Korisnik(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday, String role, boolean enabled, Timestamp lastPasswordResetDate, List<Uloga> uloge) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.uloge = uloge;
    }

    public Korisnik(KorisnikDTO dummy){
        this.username = dummy.getKorisnickoIme();
        this.password = dummy.getSifra();
        this.firstName = dummy.getIme();
        this.lastName = dummy.getPrezime();
        this.email = dummy.getEmail();
        this.role = dummy.getUloga();
        this.birthday = LocalDateTime.parse(dummy.getRodjendan(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.enabled = true;
        this.setPassword(dummy.getSifra());
        this.uloge = new ArrayList<>();
        this.ulica = dummy.getUlica();
        this.broj = dummy.getBroj();
        this.mesto = dummy.getMesto();
        this.ptt = dummy.getPtt();
        this.drzava = dummy.getDrzava();
        this.brojTelefona = dummy.getBrojTelefona();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.uloge;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

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
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(List<Uloga> uloge) {
        this.uloge = uloge;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public abstract Apoteka orElse(Object o);

    public boolean isPotvrdaEmail() {
        return potvrdaEmail;
    }

    public void setPotvrdaEmail(boolean potvrdaEmail) {
        this.potvrdaEmail = potvrdaEmail;
    }

    public boolean isPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(boolean prijavljen) {
        this.prijavljen = prijavljen;
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

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
