package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.Termin;
import projekat.FitnessCentar.repository.TerminRepository;
import java.util.List;
@Service
public class TerminService {
    @Autowired
    private TerminRepository terminRepository;

    public List<Termin> findAll() {
        List<Termin> termini = this.terminRepository.findAll();
        return termini;
    }

}
