package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projekat.FitnessCentar.entity.FC;
import projekat.FitnessCentar.entity.FCDTO;
import projekat.FitnessCentar.entity.Trening;
import projekat.FitnessCentar.entity.TreningDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/trening")
public class TreningController {
/*
    @Autowired
    private TreningService treningService;

   @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTO>> sviTreninzi() {
        List<Trening> lista = this.treningService.findAll();
        List<TreningDTO> trList=new ArrayList<>();

        for (Trening tr:lista)
        {
            TreningDTO trjedan=new TreningDTO(tr.getNaziv(),tr.getOpis(),tr.);

            fcList.add(fcjedan);
        }
        return new ResponseEntity<>(fcList, HttpStatus.OK);

    }*/
}
