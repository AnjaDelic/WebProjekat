package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.FC;
import projekat.FitnessCentar.repository.FCRepository;

import java.util.List;

@Service
public class FCService {

    @Autowired
    private FCRepository fcRepository;

    public FC findOne(Long id){
        FC fit=fcRepository.getOne(id);
        return fit;

    }

    public FC createFC(FC fitnesscentar) throws Exception {
        if (fitnesscentar.getId()!=null)
        {
            throw new Exception("ID must be null");
        }
        FC novifitnesscentar=this.fcRepository.save(fitnesscentar);
        return  novifitnesscentar;
    }

    public void deleteFC(Long id)
    {
        this.fcRepository.deleteById(id);
    }

    public FC updateFC(FC fitnesscentar) throws Exception {

        FC probafitnesscentar = this.fcRepository.getOne(fitnesscentar.getId());

        if(probafitnesscentar==null) //provera da li u bazi postoji takav fc
        {
            throw new Exception("Ne postoji FC");
        }
        //promena vrednosti
        if(fitnesscentar.getNaziv()!=null){ probafitnesscentar.setNaziv(fitnesscentar.getNaziv());}
        if(fitnesscentar.getAdresa()!=null){probafitnesscentar.setAdresa(fitnesscentar.getAdresa());}
        if(fitnesscentar.getEmail()!=null){probafitnesscentar.setEmail(fitnesscentar.getEmail());}
        if(fitnesscentar.getBroj()!=null){probafitnesscentar.setBroj(fitnesscentar.getBroj());}



        //cuvanje u bazu
        FC izmenjenfitnesscentar=this.fcRepository.save(probafitnesscentar);


        return izmenjenfitnesscentar;


    }

    public List<FC> findAll()
    {
        List<FC> fcList=this.fcRepository.findAll();
        return fcList;

    }

}
