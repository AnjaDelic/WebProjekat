package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.TrenerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/trener")
public class TrenerController {

    @Autowired
    private TrenerService trenerService;

    @PostMapping (value = "/login",produces = MediaType.APPLICATION_JSON_VALUE) //odgovara na post zahtev
    public ResponseEntity<Trener> getTrener(@RequestBody Trener trener) throws Exception {

        Trener tr = this.trenerService.findOne(trener.getUsername());

        if(tr==null)
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }


        Trener trener1=new Trener();
        trener1.setEmail(tr.getEmail());
        trener1.setActive(tr.isActive());
        trener1.setBirthday(tr.getBirthday());
        trener1.setUsername(tr.getUsername());
        trener1.setSurname(tr.getSurname());
        trener1.setId(tr.getId());
        trener1.setName(tr.getName());
        trener1.setPassword(tr.getPassword());
        trener1.setPhone(tr.getPhone());
        trener1.setProsek(tr.getProsek());

        return new ResponseEntity<>(trener1, HttpStatus.OK);
    }


    //dodavanje trenera
    @PostMapping(value = "/post",consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trener> createTrener(@RequestBody TrenerDTO c) throws Exception{

        TrenerDTO tr = new TrenerDTO(c.getUsername(),c.getPassword(),c.getName(),c.getSurname(),c.getPhone(),c.getEmail(),c.getBirthday());

        Trener trener1=new Trener();
        trener1.setEmail(tr.getEmail());
        trener1.setBirthday(tr.getBirthday());
        trener1.setUsername(tr.getUsername());
        trener1.setSurname(tr.getSurname());
        trener1.setName(tr.getName());
        trener1.setPassword(tr.getPassword());
        trener1.setPhone(tr.getPhone());
        trener1.setActive(false);
        trener1.setProsek(0.0);


        Trener noviTrener= trenerService.createTrener(trener1);

        return new ResponseEntity<>(noviTrener, HttpStatus.CREATED);
    }

    //brisanje trenera
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteTrener(@PathVariable Long id) throws Exception {
        Trener forDelete = this.trenerService.findOne1(id);
        if (forDelete == null) {
            return new ResponseEntity<>("ne postoji",HttpStatus.NOT_FOUND);
        }
        this.trenerService.deleteTrener(id);
        return new ResponseEntity<>("uspesno obrisan",HttpStatus.OK); //odg 204 uspesno brisanje
    }

    //izmena clan
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Trener> updateTrainer(@PathVariable Long id) throws Exception
     {
         Trener trener = this.trenerService.findOne1(id);
         if (trener == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         trener.setActive(true);
         Trener savedTrener = this.trenerService.updateTrener(trener);
         return new ResponseEntity<Trener>(savedTrener ,HttpStatus.OK);
     }

     @GetMapping ( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrenerZahteviDTO>> sviTreneri() {
        List<Trener> lista = this.trenerService.findAll();
        List<TrenerZahteviDTO> trList=new ArrayList<>();

        for (Trener tr:lista)
        {
            if(tr.isActive()==false){
          TrenerZahteviDTO trjedan = new TrenerZahteviDTO(tr.getName(),tr.getSurname(),tr.getId());
            trList.add(trjedan); }
        }
        return new ResponseEntity<>(trList,HttpStatus.OK);

    }

}
