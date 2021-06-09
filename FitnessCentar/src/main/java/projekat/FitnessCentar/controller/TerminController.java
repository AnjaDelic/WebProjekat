package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.TerminService;
import projekat.FitnessCentar.service.TreningService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/api/termin")
public class TerminController {

    @Autowired
    private TerminService terminService;


    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTO>> sviTr() {
        List<Termin> terminList = this.terminService.findAll();
        List<TreningDTO> dtoList=new ArrayList<>();
       for(Termin tr:terminList){

           TreningDTO treningDTO=new TreningDTO(tr.getTrening().getNaziv(),tr.getTrening().getTip(),
                                tr.getTrening().getOpis(),tr.getCena(),tr.getTrening().getTrajanje(),
                                tr.getPocetak(),tr.getKraj());
           dtoList.add(treningDTO);

       }

        return new ResponseEntity<>(dtoList,HttpStatus.OK);

    }

    @PostMapping (value = "/pretraga",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTO>> pretraga(@RequestBody TreningDTO tr) throws Exception {
        TreningDTO treningDTO=new TreningDTO(tr.getNaziv(),tr.getTip(),tr.getOpis(),tr.getCena(),tr.getTrajanje(),tr.getPocetak(),tr.getKraj());

        List<TreningDTO> lista=terminService.findSpec(treningDTO);

        return new ResponseEntity<>(lista,HttpStatus.OK);

    }




}
