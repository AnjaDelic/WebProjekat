package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.FCService;
import projekat.FitnessCentar.service.TreningService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/trening")
public class TreningController {

    @Autowired
   private TreningService treningService;



   @GetMapping(value = "/{idFC}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTO>> sviTreninzi(@PathVariable Long idFC) {
        List<Trening> lista = this.treningService.findAll();
        List<TreningDTO> trList=new ArrayList<>();


        for (Trening tr:lista)
        {
            for(Termin t:tr.getTermini()){
                if(t.getTrener().getFitness_centri().getId().equals(idFC)){
                    TreningDTO trjedan=new TreningDTO();
                    trjedan.setNaziv(tr.getNaziv());

                    trList.add(trjedan);
                }
            }


        }
        return new ResponseEntity<>(trList, HttpStatus.OK);

    }
}
