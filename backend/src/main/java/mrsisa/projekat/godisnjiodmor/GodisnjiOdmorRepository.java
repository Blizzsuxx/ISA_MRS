package mrsisa.projekat.godisnjiodmor;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GodisnjiOdmorRepository extends JpaRepository<GodisnjiOdmor,Long> {
}
