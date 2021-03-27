package mrsisa.projekat.stanjelijeka;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StanjeLijekaRepository extends JpaRepository<StanjeLijeka,Long> {
}
