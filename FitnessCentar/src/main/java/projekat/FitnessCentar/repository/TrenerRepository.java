package projekat.FitnessCentar.repository;

import projekat.FitnessCentar.entity.Trener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrenerRepository extends JpaRepository<Trener,Long> {

    Trener findTrenerByUsername(String username);
    Trener findTrenerById(Long  id);
    Trener findTrenerByUsernameAndPassword(String username,String password);
}