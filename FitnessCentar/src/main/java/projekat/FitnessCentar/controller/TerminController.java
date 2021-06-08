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


    @GetMapping ( produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping (value = "/pretraga",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTOPretraga>> pretraga(@RequestBody TreningDTOPretraga tr) throws Exception {
        TreningDTOPretraga treningDTO=new TreningDTOPretraga(tr.getNaziv(),tr.getTip(),tr.getOpis(),tr.getCena(),tr.getKriterijum(),tr.getTrajanje(),tr.getPocetak(),tr.getKraj());

        List<Termin> terminList = this.terminService.findAll();

      List<TreningDTOPretraga> dtoList =new ArrayList<>();

        for(Termin termin :terminList){

            if(treningDTO.getKriterijum()==1){ //po nazivu
                if(termin.getTrening().getNaziv().equals(treningDTO.getNaziv())){
                    TreningDTOPretraga trening=new TreningDTOPretraga(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                            termin.getTrening().getOpis(),termin.getCena(),0,termin.getTrening().getTrajanje(),
                            termin.getPocetak(),termin.getKraj());
                    dtoList.add(trening);

                }

            }
            else if(treningDTO.getKriterijum()==2){ //po opisu
                if(termin.getTrening().getOpis().equals(treningDTO.getOpis())){
                    TreningDTOPretraga trening=new TreningDTOPretraga(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                            termin.getTrening().getOpis(),termin.getCena(),0,termin.getTrening().getTrajanje(),
                            termin.getPocetak(),termin.getKraj());
                    dtoList.add(trening);
                }
            }
            else if(treningDTO.getKriterijum()==3){
                if(termin.getTrening().getTip().equals(treningDTO.getTip())){
                    TreningDTOPretraga trening=new TreningDTOPretraga(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                            termin.getTrening().getOpis(),termin.getCena(),0,termin.getTrening().getTrajanje(),
                            termin.getPocetak(),termin.getKraj());
                    dtoList.add(trening);
                }
            }
            else if(treningDTO.getKriterijum()==4){ //po ceni manjoj od
                if(termin.getCena()<treningDTO.getCena()){
                    TreningDTOPretraga trening=new TreningDTOPretraga(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                            termin.getTrening().getOpis(),termin.getCena(),0,termin.getTrening().getTrajanje(),
                            termin.getPocetak(),termin.getKraj());
                    dtoList.add(trening);
                }


            }
            else if(treningDTO.getKriterijum()==5){ //trajanje manje od
                if(termin.getTrening().getTrajanje()<treningDTO.getTrajanje()){
                    TreningDTOPretraga trening=new TreningDTOPretraga(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                            termin.getTrening().getOpis(),termin.getCena(),0,termin.getTrening().getTrajanje(),
                            termin.getPocetak(),termin.getKraj());
                    dtoList.add(trening);
                }

            }
            else if(treningDTO.getKriterijum()==6){ //pocetak
                if(treningDTO.getPocetak().compareTo(termin.getPocetak())<0){ //dto dolazi pre terminovog pocetka
                    TreningDTOPretraga trening=new TreningDTOPretraga(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                            termin.getTrening().getOpis(),termin.getCena(),0,termin.getTrening().getTrajanje(),
                            termin.getPocetak(),termin.getKraj());
                    dtoList.add(trening);
                }
            }
            else if(treningDTO.getKriterijum()==7){
                if(treningDTO.getKraj().compareTo(termin.getKraj())>0){
                    TreningDTOPretraga trening=new TreningDTOPretraga(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                            termin.getTrening().getOpis(),termin.getCena(),0,termin.getTrening().getTrajanje(),
                            termin.getPocetak(),termin.getKraj());
                    dtoList.add(trening);
                }
            }
            else{ return new ResponseEntity<>(HttpStatus.NOT_FOUND); }


        }

        return new ResponseEntity<>(dtoList,HttpStatus.OK);

    }




}
