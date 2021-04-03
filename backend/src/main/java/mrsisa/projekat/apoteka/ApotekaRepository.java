package mrsisa.projekat.apoteka;

import mrsisa.projekat.stanjelijeka.StanjeLijeka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApotekaRepository extends JpaRepository<Apoteka, Long> {


}
