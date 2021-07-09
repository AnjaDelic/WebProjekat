package projekat.FitnessCentar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.FCService;
import projekat.FitnessCentar.service.SalaService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @Autowired
    private FCService fcService;

    @PutMapping(value = "/put/{id}/{idFC}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalaDTO> updateSala(@PathVariable Long id,@PathVariable Long idFC, @RequestBody Sala s) throws Exception
    {
        FC fc=this.fcService.findOne(idFC);
        Sala sala = new Sala(s.getKapacitet(),s.getOznaka());
        sala.setId(id);
        sala.setFitness_centar(fc);//proslednjen nam je njegov id

        Sala izmenjenasala= salaService.updateSala(sala);

        SalaDTO povratna = new SalaDTO(izmenjenasala.getOznaka(),izmenjenasala.getKapacitet(), izmenjenasala.getId());

        return new ResponseEntity<>(povratna, HttpStatus.OK);

    }

   @PostMapping(value = "/post/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SalaDTO> createSala(@PathVariable FC id,@RequestBody Sala s) throws Exception{


        Sala sala = new Sala(s.getKapacitet(),s.getOznaka());
        sala.setFitness_centar(id);


        Sala nova= salaService.createSala(sala);
        SalaDTO povratna=new SalaDTO(nova.getOznaka(),nova.getKapacitet(), nova.getId());

        return new ResponseEntity<>(povratna, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteTrener(@PathVariable Long id) throws Exception {
        Sala forDelete = this.salaService.findOne(id);
        if (forDelete == null) {
            return new ResponseEntity<>("ne postoji",HttpStatus.NOT_FOUND);
        }
        this.salaService.deleteSala(id);
        return new ResponseEntity<>("uspesno obrisan",HttpStatus.OK); //odg 204 uspesno brisanje
    }

    @GetMapping(value ="/{idFC}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SalaDTO>> sveSale(@PathVariable Long idFC) {
        List<Sala> lista = this.salaService.findAll();
        List<SalaDTO> salaDTOS=new ArrayList<>();

        for (Sala s:lista)
        {
            if(s.getFitness_centar().getId().equals(idFC)){
            SalaDTO jedan=new SalaDTO();
           jedan.setOznaka(s.getOznaka());

            salaDTOS.add(jedan);
        }
        }
        return new ResponseEntity<>(salaDTOS, HttpStatus.OK);

    }

}
