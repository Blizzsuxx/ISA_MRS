package mrsisa.projekat.poseta;

import mrsisa.projekat.apoteka.Apoteka;
import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosetaRepository extends JpaRepository<Poseta, Long> {

    @Query("SELECT n FROM Poseta n WHERE n.pacijent.id =?1")
    public List<Poseta> findAllByPacijentId(int id);
}
