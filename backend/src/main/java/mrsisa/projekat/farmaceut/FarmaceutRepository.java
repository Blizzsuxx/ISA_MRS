package mrsisa.projekat.farmaceut;


import mrsisa.projekat.dermatolog.Dermatolog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaceutRepository extends JpaRepository<Farmaceut,Integer> {
    @Query("SELECT n FROM Farmaceut n WHERE n.id =?1")
    public Farmaceut findByIdD(int id);
}
