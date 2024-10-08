package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.ClanService;
import projekat.FitnessCentar.service.OcenaService;
import projekat.FitnessCentar.service.TerminService;
import projekat.FitnessCentar.service.TrenerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class OcenaController {
    @Autowired
    private OcenaService ocenaService;
    @Autowired
    private ClanService clanService;
    @Autowired
    private TerminService terminService;
    @Autowired
    private TrenerService trenerService;

    @PostMapping(value = "/pretragaNeocenjenih/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTOPretraga>> pretragaO(@PathVariable Long id) throws Exception {
        Clan clan=this.clanService.findOneID(id);
        List<TreningDTOPretraga> terminList=new ArrayList<>();
        Set<Termin> terminSet= clan.getOdradjeniTermini();


       for(Termin tr:terminSet){

                if(!clan.getOcenjeniTermini().contains(tr)){
               TreningDTOPretraga treningDTO = new TreningDTOPretraga(tr.getTrening().getNaziv(), tr.getTrening().getTip(),
                       tr.getTrening().getOpis(), tr.getCena(), tr.getTrening().getTrajanje(),
                       tr.getPocetak(), tr.getKraj(), tr.getId());
               terminList.add(treningDTO);
                                }


        }

        return new ResponseEntity<>(terminList, HttpStatus.OK);

    }

    @PostMapping(value = "/pretragaOcenjenih/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTO>> pretragaN(@PathVariable Long id) throws Exception {
      Clan clan=this.clanService.findOneID(id);

        List<TreningDTO> terminList=new ArrayList<>();
        List<Ocena> ocenaList=ocenaService.findAll();
        for(Termin o:clan.getOcenjeniTermini()){

                TreningDTO treningDTO = new TreningDTO(o.getTrening().getNaziv(), o.getTrening().getTip(),
                        o.getTrening().getOpis(), o.getCena(), o.getTrening().getTrajanje(),
                        o.getPocetak(), o.getKraj());
                terminList.add(treningDTO);

        }

        return new ResponseEntity<>(terminList, HttpStatus.OK);

    }

    @PutMapping (value = "/api/ocena/put")
    public ResponseEntity<OcenaDTO> update(@RequestBody OcenaDTO o) throws Exception{
        Clan clan=this.clanService.findOneID(o.getIdClan());
        Termin termin=this.terminService.findOneID(o.getIdTermin());
        Ocena ocena=new Ocena(clan,termin);
        ocena.setOcena(o.getOcena());

        //jel vec ocenjen
        List<Ocena> ocenaList=ocenaService.findAll();
        for(Ocena o1:ocenaList){
            if(o1.getClan().getId()==o.getIdClan() && o1.getTermin().getId()==o.getIdTermin()){
                OcenaDTO vrati=new OcenaDTO(0.0,o1.getClan().getId(),o1.getTermin().getId());
                return new ResponseEntity<>(vrati,HttpStatus.OK);
            }

        }



        Set<Termin>ocenjeniTermini=clan.getOcenjeniTermini();
        Set<Clan> oceniliClanovi=termin.getOceniliClanovi();
        Set<Ocena> cl=clan.getOcene();
        Set<Ocena> ocene=termin.getOcene();

        ocenjeniTermini.add(termin);
        oceniliClanovi.add(clan);
        ocene.add(ocena);

        Clan ispravljen=new Clan();
        ispravljen.setId(clan.getId());
        ispravljen.setActive(clan.isActive());
        ispravljen.setUsername(clan.getUsername());
        ispravljen.setSurname(clan.getSurname());
        ispravljen.setPhone(clan.getPhone());
        ispravljen.setPassword(clan.getPassword());
        ispravljen.setOcenjeniTermini(ocenjeniTermini);
        ispravljen.setOcene(cl);
        ispravljen.setName(clan.getName());
        ispravljen.setOdradjeniTermini(clan.getOdradjeniTermini());
        ispravljen.setPrijavljeniTermini(clan.getPrijavljeniTermini());
        ispravljen.setEmail(clan.getEmail());
        Clan azuriran=this.clanService.updateClan(ispravljen);

        Termin ispravljenT=new Termin();
        ispravljenT.setOcene(termin.getOcene());
        ispravljenT.setOceniliClanovi(termin.getOceniliClanovi());
        ispravljenT.setOdradiliClanovi(termin.getOdradiliClanovi());
        ispravljenT.setSala(termin.getSala());
        ispravljenT.setTrener(termin.getTrener());
        ispravljenT.setPrijaviliClanovi(termin.getPrijaviliClanovi());
        ispravljenT.setCena(termin.getCena());
        ispravljenT.setKraj(termin.getKraj());
        ispravljenT.setPocetak(termin.getPocetak());
        ispravljenT.setTrening(termin.getTrening());
        ispravljenT.setId(termin.getId());
        Termin azuriran1=this.terminService.updateTermin(ispravljenT);

        int brOcena=0;
        Trener noviTR= termin.getTrener();

        for(Ocena ocena1:ocene){
            if(ocena1.getTermin().getTrener().equals(noviTR)){
                brOcena++;
            }
        }

        double prosecna=(noviTR.getProsek()+ o.getOcena())/brOcena;


        noviTR.setId(noviTR.getId());
        noviTR.setTermini(noviTR.getTermini());
        noviTR.setFitness_centri(noviTR.getFitness_centri());
        noviTR.setActive(noviTR.isActive());
        noviTR.setEmail(noviTR.getEmail());
        noviTR.setProsek(prosecna);
        noviTR.setBirthday(noviTR.getBirthday());
        noviTR.setName(noviTR.getName());
        noviTR.setPassword(noviTR.getPassword());
        noviTR.setPhone(noviTR.getPhone());
        noviTR.setSurname(noviTR.getSurname());
        noviTR.setUsername(noviTR.getUsername());
        Trener izmenjen =this.trenerService.updateTrener(noviTR);



      OcenaDTO povratna=new OcenaDTO(ocena.getOcena(), ocena.getClan().getId(),ocena.getTermin().getId());

    return new ResponseEntity<>(povratna,HttpStatus.CREATED);
    }

}
