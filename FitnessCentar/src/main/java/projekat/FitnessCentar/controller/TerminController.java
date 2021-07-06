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
    public ResponseEntity<List<TreningDTO>> getOdradjeni(@PathVariable Long id) throws Exception{

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

    @GetMapping (value = "/getPrijavljeni/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTOPretraga>> getPrijavljeni(@PathVariable Long id) throws Exception{

        Clan clan=this.clanService.findOneID(id);

        Set<Termin> terminSet= clan.getPrijavljeniTermini();
        List<TreningDTOPretraga> terminList=new ArrayList<>();
        for(Termin tr:terminSet){

            TreningDTOPretraga treningDTO=new TreningDTOPretraga(tr.getTrening().getNaziv(),tr.getTrening().getTip(),
                    tr.getTrening().getOpis(),tr.getCena(),tr.getTrening().getTrajanje(),
                    tr.getPocetak(),tr.getKraj(), tr.getId());
            terminList.add(treningDTO);

        }


        return new ResponseEntity<>(terminList, HttpStatus.OK);
    }

    @PutMapping (value = "/prijavljeniPut/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreningDTO> updatePrijavljeni(@PathVariable Long id, @RequestBody ClanID idClana) throws Exception
    {
        Clan clan=this.clanService.findOneID(idClana.getIdClana());
        Termin termin=this.terminService.findOneID(id);


        Set<Termin> clanSet=clan.getPrijavljeniTermini();
        Set<Clan> terminSet=termin.getPrijaviliClanovi();



        Set<Termin>prijaviliTermini=clan.getPrijavljeniTermini();
        Set<Clan> prijaviliClanovi=termin.getPrijaviliClanovi();


        prijaviliTermini.remove(termin);
        prijaviliClanovi.remove(clan);



        Clan ispravljen=new Clan();
        ispravljen.setId(clan.getId());
        ispravljen.setActive(clan.isActive());
        ispravljen.setUsername(clan.getUsername());
        ispravljen.setSurname(clan.getSurname());
        ispravljen.setPhone(clan.getPhone());
        ispravljen.setPassword(clan.getPassword());
        ispravljen.setOcenjeniTermini(clan.getOcenjeniTermini());
        ispravljen.setOcene(clan.getOcene());
        ispravljen.setName(clan.getName());
        ispravljen.setOdradjeniTermini(clan.getOdradjeniTermini());
        ispravljen.setPrijavljeniTermini(prijaviliTermini);
        ispravljen.setEmail(clan.getEmail());

        Termin ostaje=new Termin();

        ostaje.setOcene(termin.getOcene());
        ostaje.setOceniliClanovi(termin.getOceniliClanovi());
        ostaje.setOdradiliClanovi(termin.getOdradiliClanovi());
        ostaje.setSala(termin.getSala());
        ostaje.setTrener(termin.getTrener());
        ostaje.setPrijaviliClanovi(prijaviliClanovi);
        ostaje.setCena(termin.getCena());
        ostaje.setKraj(termin.getKraj());
        ostaje.setPocetak(termin.getPocetak());
        ostaje.setTrening(termin.getTrening());
        ostaje.setId(termin.getId());

        //cuvanje u bazu
        Clan azuriran=this.clanService.updateClan(ispravljen);
        Termin azuriran1=this.terminService.updateTermin(ostaje);

        TreningDTO treningDTO=new TreningDTO(azuriran1.getTrening().getNaziv(),azuriran1.getTrening().getTip(),
                azuriran1.getTrening().getOpis(),azuriran1.getCena(),azuriran1.getTrening().getTrajanje(),
                azuriran1.getPocetak(),azuriran1.getKraj());

        return new ResponseEntity<>(treningDTO,HttpStatus.CREATED);

    }


}
