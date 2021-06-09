package projekat.FitnessCentar.repository;

import projekat.FitnessCentar.entity.Administrator;
import projekat.FitnessCentar.entity.Clan;

import org.springframework.data.jpa.repository.JpaRepository;
import projekat.FitnessCentar.entity.ClanDTO;

public interface ClanRepository extends JpaRepository<Clan,Long> {
    Clan findAClanByUsername(String username);
    Clan findClanByUsernameAndPassword(String username,String password);
}