package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.FC;
import projekat.FitnessCentar.entity.FCDTO;
import projekat.FitnessCentar.entity.Sala;
import projekat.FitnessCentar.entity.SalaDTO;
import projekat.FitnessCentar.service.FCService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/api/FC")
public class FCController {
    @Autowired
    private FCService fcService;

    @GetMapping (value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FC> getFC(@PathVariable Long id) throws Exception {

        FC fc = this.fcService.findOne(id);

        FC fitnesscentar = new FC();
        fitnesscentar.setId(fc.getId());
        fitnesscentar.setNaziv(fc.getNaziv());
        fitnesscentar.setBroj(fc.getBroj());
        fitnesscentar.setAdresa(fc.getAdresa());
        fitnesscentar.setEmail(fc.getEmail());


        return new ResponseEntity<>(fitnesscentar, HttpStatus.OK);
    }

    @GetMapping (value = "/getSale/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalaDTO>> getSale(@PathVariable Long id) throws Exception{

        FC fc = this.fcService.findOne(id);
        Set<Sala>sale= fc.getSale();
        List<SalaDTO> salaList=new ArrayList<>();
        for (Sala s:sale)
        {
            SalaDTO jedan=new SalaDTO(s.getOznaka(),s.getKapacitet(),s.getId());
            salaList.add(jedan);
        }




        return new ResponseEntity<>(salaList, HttpStatus.OK);
    }


    //dodavanje FC
    @PostMapping(value = "/post", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FC> createFC(@RequestBody FC fc) throws Exception{

        FC fitnesscentar = new FC(fc.getNaziv(),fc.getAdresa(),fc.getBroj(),fc.getEmail());


        FC novifitnesscentar= fcService.createFC(fitnesscentar);

        return new ResponseEntity<>(novifitnesscentar, HttpStatus.CREATED);
    }

    //brisanje FC
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteFC(@PathVariable Long id)
    {
        this.fcService.deleteFC(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //odg 204 uspesno brisanje
    }

    //izmena FC
    @PutMapping(value = "/put/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FCDTO> updateFC(@PathVariable Long id, @RequestBody FC fc) throws Exception
    {
        FC fitnesscentar = new FC(fc.getNaziv(),fc.getAdresa(),fc.getBroj(),fc.getEmail());
        fitnesscentar.setId(id); //proslednjen nam je njegov id

        FC izmenjenfitnesscentar= fcService.updateFC(fitnesscentar);
        FCDTO povratni =new FCDTO(izmenjenfitnesscentar.getNaziv(),izmenjenfitnesscentar.getAdresa(),izmenjenfitnesscentar.getBroj(),izmenjenfitnesscentar.getEmail(),izmenjenfitnesscentar.getId());
        return new ResponseEntity<>(povratni,HttpStatus.OK);

    }

    @GetMapping ( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FCDTO>> sviFC() {
        List<FC> lista = this.fcService.findAll();
        List<FCDTO> fcList=new ArrayList<>();

        for (FC fc:lista)
        {
            FCDTO fcjedan=new FCDTO(fc.getNaziv(),fc.getAdresa(),fc.getBroj(),fc.getEmail(),fc.getId());
            fcList.add(fcjedan);
        }
        return new ResponseEntity<>(fcList,HttpStatus.OK);

    }

}
