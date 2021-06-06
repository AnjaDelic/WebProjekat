package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.Administrator;
import projekat.FitnessCentar.entity.Clan;
import projekat.FitnessCentar.entity.Trener;
import projekat.FitnessCentar.service.TrenerService;

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
    public ResponseEntity<Trener> createTrener(@RequestBody Trener c) throws Exception{

        Trener trener = new Trener(c.getUsername(),c.getPassword(),c.getName(),c.getSurname(),c.getPhone(),c.getEmail(),c.getBirthday(),c.isActive(),c.getProsek());


        Trener noviTrener= trenerService.createTrener(trener);

        return new ResponseEntity<>(noviTrener, HttpStatus.CREATED);
    }

    //brisanje trenera
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteTrener(@PathVariable Long id)
    {
        this.trenerService.deleteTrener(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //odg 204 uspesno brisanje
    }

    //izmena clan
    @PutMapping(value = "/put/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trener> updateTrener(@PathVariable Long id, @RequestBody Trener c) throws Exception
    {
        Trener trener = new Trener(c.getUsername(),c.getPassword(),c.getName(),c.getSurname(),c.getPhone(),c.getEmail(),c.getBirthday(),c.isActive(),c.getProsek());


        trener.setId(id); //proslednjen nam je njegov id

        Trener izmenjencTrener= trenerService.updateTrener(trener);

        return new ResponseEntity<>(izmenjencTrener,HttpStatus.OK);

    }

}
