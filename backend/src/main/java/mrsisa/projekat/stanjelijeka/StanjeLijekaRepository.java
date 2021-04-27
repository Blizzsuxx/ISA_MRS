package mrsisa.projekat.stanjelijeka;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StanjeLijekaRepository extends JpaRepository<StanjeLijeka,Long> {

    @Query("SELECT n FROM StanjeLijeka n WHERE n.apoteka.id =?1")
    public List<StanjeLijeka> nadjiStanja(Long id);
}
