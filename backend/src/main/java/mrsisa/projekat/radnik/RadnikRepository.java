package mrsisa.projekat.radnik;

import mrsisa.projekat.adresa.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadnikRepository extends JpaRepository<Radnik, Integer> {
}
