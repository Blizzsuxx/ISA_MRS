package mrsisa.projekat.erecept;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EreceptRepository extends JpaRepository<Erecept, Integer> {
    @Query("select s from Erecept s where s.pacijent.id = ?1")
    List<Erecept> findAllByUserID(int i);
}
