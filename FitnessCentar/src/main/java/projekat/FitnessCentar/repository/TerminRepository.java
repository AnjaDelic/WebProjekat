package projekat.FitnessCentar.repository;

import projekat.FitnessCentar.entity.Termin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TerminRepository extends JpaRepository<Termin,Long> {

    List<Termin> findTerminByCenaIsLessThanEqualAndPocetakAfter(int cena,Date pocetak);

}