package mrsisa.projekat.korisnik;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
    Korisnik findByUsername(String username);
    void deleteByUsername(String username);
    Korisnik findByEmailIgnoreCase(String email);
}
