package mrsisa.projekat.pacijent;

import mrsisa.projekat.adresa.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacijentRepository extends JpaRepository<Adresa, String> {
}
