package projekat.FitnessCentar.repository;

import projekat.FitnessCentar.entity.Clan;
import projekat.FitnessCentar.entity.Ocena;

import org.springframework.data.jpa.repository.JpaRepository;
import projekat.FitnessCentar.entity.Termin;

public interface OcenaRepository extends JpaRepository<Ocena,Long> {
    Ocena findOcenaByClanAndTermin(Clan clan,Termin termin);
    Ocena findOcenaByClan(Clan clan);
}
