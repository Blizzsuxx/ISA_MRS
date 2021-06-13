package mrsisa.projekat.dermatolog;

import mrsisa.projekat.poseta.Poseta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DermatologRepository extends JpaRepository<Dermatolog, Integer> {

    @Query("SELECT n FROM Dermatolog n WHERE n.id =?1")
    public Dermatolog findByIdD(int id);

}
