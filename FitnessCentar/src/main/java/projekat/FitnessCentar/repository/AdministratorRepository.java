package projekat.FitnessCentar.repository;

import org.springframework.data.domain.Example;
import projekat.FitnessCentar.entity.Administrator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdministratorRepository extends JpaRepository<Administrator,Long> {

    Administrator findAdministratorByUsername(String username);
}
