package mrsisa.projekat.tipPenala;

import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenalRepository extends JpaRepository<Pacijent, String> {


     @Query("select p from Penal p where p.idKorisnika = ?1")
     public List<Penal> findAllByIdPacijent(String i);
}
