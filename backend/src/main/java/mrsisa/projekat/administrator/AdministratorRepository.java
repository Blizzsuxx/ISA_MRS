package mrsisa.projekat.administrator;

import mrsisa.projekat.adresa.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, String> {
}
