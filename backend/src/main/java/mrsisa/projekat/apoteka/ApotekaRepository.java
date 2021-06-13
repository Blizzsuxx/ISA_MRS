package mrsisa.projekat.apoteka;

import mrsisa.projekat.pacijent.Pacijent;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApotekaRepository extends JpaRepository<Apoteka, Long> {

    @Query("select s from Apoteka s where s.id = ?1")
    public Apoteka findOneById(long l);


}
