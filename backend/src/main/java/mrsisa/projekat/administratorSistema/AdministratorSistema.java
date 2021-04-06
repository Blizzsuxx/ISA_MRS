package mrsisa.projekat.administratorSistema;

import javax.persistence.*;

import mrsisa.projekat.administrator.Administrator;

import java.time.LocalDateTime;

@Entity
@Table(name = "administratoriSistema")
@PrimaryKeyJoinColumn(name = "administrator")
public class AdministratorSistema extends Administrator{

    public AdministratorSistema () {}

    public AdministratorSistema(String username, String password, String firstName, String lastName, String email, LocalDateTime birthday) {
        super(username, password, firstName, lastName, email, birthday);
    }
}
