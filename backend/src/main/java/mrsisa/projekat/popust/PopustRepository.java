package mrsisa.projekat.popust;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PopustRepository extends JpaRepository<Popust, Long> {
    Popust findById(int id);
}
