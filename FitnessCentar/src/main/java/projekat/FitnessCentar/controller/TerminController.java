package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.ClanService;
import projekat.FitnessCentar.service.OcenaService;
import projekat.FitnessCentar.service.TerminService;
import projekat.FitnessCentar.service.TreningService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/api/termin")
public class TerminController {

    @Autowired
    private TerminService terminService;

    @Autowired
    private ClanService clanService;

    @Autowired
    private OcenaService ocenaService;

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



    @GetMapping (value = "/getOdradjeni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTO>> getSale(@PathVariable Long id) throws Exception{

        Clan clan=this.clanService.findOneID(id);

        Set<Termin> terminSet= clan.getOdradjeniTermini();
        List<TreningDTO> terminList=new ArrayList<>();
        for(Termin tr:terminSet){

            TreningDTO treningDTO=new TreningDTO(tr.getTrening().getNaziv(),tr.getTrening().getTip(),
                    tr.getTrening().getOpis(),tr.getCena(),tr.getTrening().getTrajanje(),
                    tr.getPocetak(),tr.getKraj());
            terminList.add(treningDTO);

        }


        return new ResponseEntity<>(terminList, HttpStatus.OK);
    }




}
