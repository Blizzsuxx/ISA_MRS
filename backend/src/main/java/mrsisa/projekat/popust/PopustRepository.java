package mrsisa.projekat.popust;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PopustRepository extends JpaRepository<Popust, Long> {

    @Query("select s from Popust s where s.id = ?1")
    Popust findById(int id);
}
