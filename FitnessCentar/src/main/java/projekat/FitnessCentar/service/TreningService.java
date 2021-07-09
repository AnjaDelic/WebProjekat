package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.Administrator;
import projekat.FitnessCentar.entity.Trening;
import projekat.FitnessCentar.repository.TerminRepository;
import projekat.FitnessCentar.repository.TreningRepository;

import java.util.List;

@Service
public class TreningService {
    @Autowired
    private TreningRepository treningRepository;

   public List<Trening> findAll(){
       List<Trening> povratak = treningRepository.findAll();
       return povratak;

    }

    public Trening find(String naziv){
       Trening povratak =treningRepository.findTreningByNaziv(naziv);
       return  povratak;
    }

    public Trening create(Trening trening) throws Exception{
        if (trening.getId()!=null)
        {
            throw new Exception("ID must be null");
        }
        Trening novi=this.treningRepository.save(trening);
        return  novi;

    }

}
