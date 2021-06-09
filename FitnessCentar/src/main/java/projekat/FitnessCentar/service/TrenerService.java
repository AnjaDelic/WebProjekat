package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.Administrator;
import projekat.FitnessCentar.entity.Trener;
import projekat.FitnessCentar.repository.TrenerRepository;

import java.util.List;

@Service
public class TrenerService {

    @Autowired
    private TrenerRepository trenerRepository;

    public Trener createTrener(Trener tr) throws Exception {
        if (tr.getId()!=null)
        {
            throw new Exception("ID must be null");
        }
        Trener noviTrener=this.trenerRepository.save(tr);
        return  noviTrener;
    }

    public void deleteTrener(Long id)
    {
        this.trenerRepository.deleteById(id);
    }

    public Trener updateTrener(Trener tr) throws Exception {

        Trener probaTr = this.trenerRepository.getOne(tr.getId()); //dobavljamo tog zaposlenog

        if(probaTr==null) //provera da li u bazi postoji takav fc
        {
            throw new Exception("Ne postoji FC");
        }
        //promena vrednosti
        probaTr.setActive(tr.isActive());
        probaTr.setBirthday(tr.getBirthday());
        probaTr.setEmail(tr.getEmail());
        probaTr.setName(tr.getName());
        probaTr.setPassword(tr.getPassword());
        probaTr.setPhone(tr.getPhone());
        probaTr.setSurname(tr.getSurname());
        probaTr.setUsername(tr.getUsername());
        probaTr.setProsek(tr.getProsek());


        //cuvanje u bazu
        Trener izmenjenTrener=this.trenerRepository.save(probaTr);
        return izmenjenTrener;


    }

    public Trener findOne(String username) throws Exception {

        Trener tr = this.trenerRepository.findTrenerByUsername(username);


        return tr;


    }

    public  Trener findOne1(Long id) throws Exception{
        Trener trener=this.trenerRepository.findTrenerById(id);
        return trener;
    }

    public List<Trener> findAll()
     {
        List<Trener> lista=  this.trenerRepository.findAll();
        return lista;
     }

    public Trener findByUsernameAndPassword(String username, String password){
        Trener trener=trenerRepository.findTrenerByUsernameAndPassword(username,password);
        return trener;
    }


}
