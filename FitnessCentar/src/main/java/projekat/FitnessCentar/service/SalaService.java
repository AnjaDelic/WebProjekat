package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.FC;
import projekat.FitnessCentar.entity.Sala;
import projekat.FitnessCentar.entity.Trener;
import projekat.FitnessCentar.repository.SalaRepository;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;


    public Sala updateSala(Sala sala) throws Exception {

        Sala proba = this.salaRepository.getOne(sala.getId());

        if(proba==null) //provera da li u bazi postoji takav fc
        {
            throw new Exception("Ne postoji Sala");
        }
        //promena vrednosti
        if(sala.getKapacitet()> -1 ){ proba.setKapacitet(sala.getKapacitet());}
        if(sala.getOznaka()!=null){proba.setOznaka(sala.getOznaka());}
        proba.setTermini(sala.getTermini());
        proba.setId(sala.getId());

        proba.setFitness_centar(sala.getFitness_centar());

        //cuvanje u bazu
        Sala izmenjena=this.salaRepository.save(proba);

        return izmenjena;


    }

    public Sala createSala(Sala sala) throws Exception {
        if (sala.getId()!=null)
        {
            throw new Exception("ID must be null");
        }
        Sala nova=this.salaRepository.save(sala);
        return  nova;
    }

    public void deleteSala(Long id)
    {
        this.salaRepository.deleteById(id);
    }

    public Sala findOne(Long id) throws Exception{
        Sala sala=this.salaRepository.getOne(id);
        return sala;
    }

    public List<Sala> findAll(){
        List<Sala>povratak=this.salaRepository.findAll();
        return povratak;
    }

    public Sala find(String oznaka){
        Sala povratak=this.salaRepository.findSalaByOznaka(oznaka);
        return povratak;
    }

}
