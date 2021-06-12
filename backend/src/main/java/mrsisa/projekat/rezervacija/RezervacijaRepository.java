package mrsisa.projekat.rezervacija;

import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    @Query("select s from Rezervacija s where s.pacijent.id = ?1 ")
    List<Rezervacija> findAllByUserId(int i);

    @Query("select s from Rezervacija s where s.id = ?1 ")
    Rezervacija findById1(long parseLong);
    @Query("select s from Rezervacija s where s.pacijent.username = ?1 ")
    List<Rezervacija> findAllByUsername(String username);
}
