package mrsisa.projekat.slobodanTermin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlobodanTerminRepository extends JpaRepository<SlobodanTermin,Long> {

    @Query("SELECT s FROM SlobodanTermin  s")
    List<SlobodanTermin> findAll();

    @Query("SELECT s FROM SlobodanTermin s WHERE s.id = ?1")
    SlobodanTermin findOneById(long parseLong);
}
