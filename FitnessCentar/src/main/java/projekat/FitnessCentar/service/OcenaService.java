package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.Clan;
import projekat.FitnessCentar.entity.FC;
import projekat.FitnessCentar.entity.Ocena;
import projekat.FitnessCentar.entity.Termin;
import projekat.FitnessCentar.repository.OcenaRepository;

import java.util.List;

@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    public Ocena findOne(Clan clan, Termin termin){
        Ocena fit=ocenaRepository.findOcenaByClanAndTermin(clan,termin);
        return fit;

    }

    public Ocena find(Clan clan){
        Ocena fit=ocenaRepository.findOcenaByClan(clan);
        return fit;

    }
    public List<Ocena> findAll() {
        List<Ocena> ocenaList = this.ocenaRepository.findAll();
        return ocenaList;
    }

    public Ocena add(Ocena o)  throws Exception {

        if (o.getId()!=null)
        {
            throw new Exception("ID must be null");
        }
        Ocena nova=new Ocena();
        nova.setClan(o.getClan());
        nova.setTermin(o.getTermin());
        nova.setOcena(o.getOcena());

        Ocena izmenjena=this.ocenaRepository.save(nova);


        return  izmenjena;

    }


}
