package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.FCService;
import projekat.FitnessCentar.service.TrenerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/trener")
public class TrenerController {

    @Autowired
    private TrenerService trenerService;

    @Autowired
    private FCService fcService;

    @PostMapping (value = "/login",produces = MediaType.APPLICATION_JSON_VALUE) //odgovara na post zahtev
    public ResponseEntity<Trener> getTrener(@RequestBody Trener trener) throws Exception {

        Trener tr = this.trenerService.findByUsernameAndPassword(trener.getUsername(),trener.getPassword());

        if(tr==null)
        {

            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
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


        return new ResponseEntity<>(trener1, HttpStatus.ACCEPTED);
    }

    @GetMapping (value = "/sviTR",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trener>> sviTR() {
        List<Trener> lista = this.trenerService.findAll();
        List<Trener> povratnaLista=new ArrayList<>();

        for(Trener t:lista){

            Trener trenerDTO=new Trener();
            trenerDTO.setName(t.getName());
            trenerDTO.setSurname(t.getSurname());
            trenerDTO.setActive(t.isActive());
            trenerDTO.setId(t.getId());
            trenerDTO.setUsername(t.getUsername());
            povratnaLista.add(trenerDTO);

        }


        return new ResponseEntity<>(povratnaLista,HttpStatus.OK);

    }


    //dodavanje trenera
    @PostMapping(value = "/post", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrenerDTO> createTrener(@RequestBody TrenerDTO c) throws Exception{

        TrenerDTO tr = new TrenerDTO(c.getName(),c.getSurname(),c.getUsername(),c.getPassword(),c.getEmail(),c.getPhone(),c.getBirthday(),c.getVrati());



        List<Trener> trenerList = trenerService.findAll();
        for(Trener trener : trenerList) {
            if(trener.getUsername().equalsIgnoreCase(c.getUsername())) {
                TrenerDTO vrati = new TrenerDTO("","","","","","",tr.getBirthday(),1);
                return new ResponseEntity <>(vrati, HttpStatus.CREATED);
            }
            if(trener.getEmail().equalsIgnoreCase(c.getEmail())) {
                TrenerDTO vrati = new TrenerDTO("","","","","","",tr.getBirthday(),2);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }
            if(trener.getPhone().equalsIgnoreCase(c.getPhone())) {
                TrenerDTO vrati = new TrenerDTO("","","","","","",tr.getBirthday(),3);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }

        }

        TrenerDTO trener2=new TrenerDTO();
        trener2.setEmail(tr.getEmail());
        trener2.setBirthday(tr.getBirthday());
        trener2.setUsername(tr.getUsername());
        trener2.setSurname(tr.getSurname());
        trener2.setName(tr.getName());
        trener2.setPassword(tr.getPassword());
        trener2.setPhone(tr.getPhone());
        trener2.setVrati(tr.getVrati());



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

        return new ResponseEntity<TrenerDTO>(trener2, HttpStatus.CREATED);
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
    public ResponseEntity<TrenerZahteviDTO> updateTrainer(@PathVariable Long id) throws Exception
     {
         Trener trener = this.trenerService.findOne1(id);
         if (trener == null) {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         trener.setActive(true);
         Trener savedTrener = this.trenerService.updateTrener(trener);
         TrenerZahteviDTO vrati=new TrenerZahteviDTO();
         vrati.setUsername(savedTrener.getUsername());
         vrati.setName(savedTrener.getName());
         vrati.setId(savedTrener.getId());
         vrati.setSurname(savedTrener.getSurname());


         return new ResponseEntity<>(vrati ,HttpStatus.OK);
     }

     @GetMapping ( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrenerZahteviDTO>> sviTreneri() {
        List<Trener> lista = this.trenerService.findAll();
        List<TrenerZahteviDTO> trList=new ArrayList<>();

        for (Trener tr:lista)
        {
            if(tr.isActive()==false){
          TrenerZahteviDTO trjedan = new TrenerZahteviDTO(tr.getName(),tr.getSurname(),tr.getId(),tr.getUsername());
            trList.add(trjedan); }
        }
        return new ResponseEntity<>(trList,HttpStatus.OK);

    }

    //dodavanje trenera
    @PostMapping(value = "/postAdmin/{idFC}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrenerDTO> createTrenerAdmin(@RequestBody TrenerDTO c,@PathVariable Long idFC) throws Exception{

        TrenerDTO tr = new TrenerDTO(c.getName(),c.getSurname(),c.getUsername(),c.getPassword(),c.getEmail(),c.getPhone(),c.getBirthday(),c.getVrati());
        FC fc=this.fcService.findOne(idFC);


        List<Trener> trenerList = trenerService.findAll();
        for(Trener trener : trenerList) {
            if(trener.getUsername().equalsIgnoreCase(c.getUsername())) {
                TrenerDTO vrati = new TrenerDTO("","","","","","",tr.getBirthday(),1);
                return new ResponseEntity <>(vrati, HttpStatus.CREATED);
            }
            if(trener.getEmail().equalsIgnoreCase(c.getEmail())) {
                TrenerDTO vrati = new TrenerDTO("","","","","","",tr.getBirthday(),2);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }
            if(trener.getPhone().equalsIgnoreCase(c.getPhone())) {
                TrenerDTO vrati = new TrenerDTO("","","","","","",tr.getBirthday(),3);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }

        }

        TrenerDTO trener2=new TrenerDTO();
        trener2.setEmail(tr.getEmail());
        trener2.setBirthday(tr.getBirthday());
        trener2.setUsername(tr.getUsername());
        trener2.setSurname(tr.getSurname());
        trener2.setName(tr.getName());
        trener2.setPassword(tr.getPassword());
        trener2.setPhone(tr.getPhone());
        trener2.setVrati(tr.getVrati());



        Trener trener1=new Trener();
        trener1.setEmail(tr.getEmail());
        trener1.setBirthday(tr.getBirthday());
        trener1.setUsername(tr.getUsername());
        trener1.setSurname(tr.getSurname());
        trener1.setName(tr.getName());
        trener1.setPassword(tr.getPassword());
        trener1.setPhone(tr.getPhone());
        trener1.setActive(true);
        trener1.setProsek(0.0);
        trener1.setFitness_centri(fc);


        Trener noviTrener= trenerService.createTrenerAdmin(trener1);

        return new ResponseEntity<TrenerDTO>(trener2, HttpStatus.CREATED);
    }

    @GetMapping (value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrenerDTOProfil> getTrener(@PathVariable Long id) throws Exception {

        Trener trener=this.trenerService.findOne1(id);


        TrenerDTOProfil povratni= new TrenerDTOProfil();

        povratni.setUsername(trener.getUsername());
        povratni.setEmail(trener.getEmail());
        povratni.setName(trener.getName());
        povratni.setPassword(trener.getPassword());
        povratni.setProsek(trener.getProsek());
        povratni.setSurname(trener.getSurname());
        povratni.setPhone(trener.getPhone());
        povratni.setBirthday(trener.getBirthday());
        povratni.setId(id);
        povratni.setIdFC(trener.getFitness_centri().getId());







        return new ResponseEntity<>(povratni, HttpStatus.OK);
    }

}
