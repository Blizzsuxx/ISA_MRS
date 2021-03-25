package mrsisa.projekat.korisnik;

import javax.persistence.*;

@Entity
public abstract class  Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

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
}
