package mrsisa.projekat.narudzbenica;

import mrsisa.projekat.adresa.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Long> {
}
