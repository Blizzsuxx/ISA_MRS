package mrsisa.projekat.tipPenala;

import mrsisa.projekat.pacijent.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface PenalRepository extends JpaRepository<Pacijent, String> {

     @Lock(LockModeType.PESSIMISTIC_READ)
     @Query("select p from Penal p where p.idKorisnika = ?1 ") //FOR update of p
     public List<Penal> findAllByIdPacijent(String i);


}
