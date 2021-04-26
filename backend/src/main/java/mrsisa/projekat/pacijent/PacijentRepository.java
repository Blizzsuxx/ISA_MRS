package mrsisa.projekat.pacijent;

import mrsisa.projekat.adresa.Adresa;
import mrsisa.projekat.korisnik.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, String> {

public interface PacijentRepository extends JpaRepository<Pacijent, String> {
    Pacijent findByUsername(String username);
    @Query("select s from Pacijent s where s.username = ?1")
    public Pacijent findOneByUsername(String id);

}
