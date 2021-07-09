package projekat.FitnessCentar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.FitnessCentar.entity.Sala;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala,Long> {
    Sala findSalaByOznaka(String oznaka);
}
