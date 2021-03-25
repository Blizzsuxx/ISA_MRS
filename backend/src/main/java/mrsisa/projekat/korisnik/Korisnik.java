package mrsisa.projekat.korisnik;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public abstract class  Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "birthday", nullable = false)
    private LocalDateTime birthday;
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
