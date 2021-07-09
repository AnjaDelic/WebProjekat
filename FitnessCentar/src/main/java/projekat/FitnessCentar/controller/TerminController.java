package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.*;

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
    private TreningService treningService;



    @Autowired
    private SalaService salaService;

    @Autowired
    private TrenerService trenerService;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTOPretraga>> sviTr() {
        List<Termin> terminList = this.terminService.findAll();
        List<TreningDTOPretraga> dtoList=new ArrayList<>();
       for(Termin tr:terminList){

           TreningDTOPretraga treningDTO=new TreningDTOPretraga(tr.getTrening().getNaziv(),tr.getTrening().getTip(),
                                tr.getTrening().getOpis(),tr.getCena(),tr.getTrening().getTrajanje(),
                                tr.getPocetak(),tr.getKraj(),tr.getId());
           dtoList.add(treningDTO);

       }

        return new ResponseEntity<>(dtoList,HttpStatus.OK);

    }

    @GetMapping(value = "/slobodni",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TerminDTO>> slobodno() {
        List<Termin> terminList = this.terminService.findAll();
        List<TerminDTO> dtoList=new ArrayList<>();

        int br=0;
        int brrijavljenih=0;

        for(Termin tr:terminList){
            for(Clan c:tr.getPrijaviliClanovi()){
                brrijavljenih++;
            }
            br=tr.getSala().getKapacitet()-brrijavljenih;
            TerminDTO treningDTO=new TerminDTO();
            treningDTO.setBr(br);
            treningDTO.setCena(tr.getCena());
            treningDTO.setKraj(tr.getKraj());
            treningDTO.setPocetak(tr.getPocetak());
            treningDTO.setNaziv(tr.getTrening().getNaziv());
            treningDTO.setOpis(tr.getTrening().getOpis());
            treningDTO.setTip(tr.getTrening().getTip());
            treningDTO.setTip(tr.getTrening().getTip());
            treningDTO.setId(tr.getId());
            dtoList.add(treningDTO);
        }

        return new ResponseEntity<>(dtoList,HttpStatus.OK);

    }

    @PostMapping (value = "/pretraga",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TerminDTO>> pretraga(@RequestBody TerminDTO tr) throws Exception {
        TerminDTO treningDTO=new TerminDTO();
        treningDTO.setCena(tr.getCena());
        treningDTO.setKraj(tr.getKraj());
        treningDTO.setPocetak(tr.getPocetak());
        treningDTO.setNaziv(tr.getNaziv());
        treningDTO.setTrajanje(tr.getTrajanje());
        treningDTO.setOpis(tr.getOpis());
        treningDTO.setTip(tr.getTip());
        treningDTO.setId(tr.getId());
        treningDTO.setBr(tr.getBr());

        List<TerminDTO> lista=terminService.findSpec(treningDTO);



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

    @PutMapping(value = "/prijava/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreningDTOPoruka> prijaviTrening(@PathVariable Long id, @RequestBody ClanID idClana) throws Exception
    {
        Clan clan=this.clanService.findOneID(idClana.getIdClana());
        Termin termin=this.terminService.findOneID(id);

        Set<Termin>prijaviliTermini=clan.getPrijavljeniTermini();
        Set<Clan> prijaviliClanovi=termin.getPrijaviliClanovi();



        //vec prijavio
        for(Termin c:prijaviliTermini){
            if(c.getId().equals(id)){
                TreningDTOPoruka treningDTOPoruka=new TreningDTOPoruka();
                treningDTOPoruka.setVrati(1); //ako je 1 prijavio je
                return new ResponseEntity<>(treningDTOPoruka,HttpStatus.OK);
            }
        }
        int br=0; //koliko clanova je prijavilo
        //nema mesta
            for(Clan c:prijaviliClanovi){
                br++;
            }
            if(termin.getSala().getKapacitet()==br){
                TreningDTOPoruka treningDTOPoruka=new TreningDTOPoruka();
                treningDTOPoruka.setVrati(2); //ako je 2 nema mesta
                return new ResponseEntity<>(treningDTOPoruka,HttpStatus.OK);
            }


        prijaviliTermini.add(termin);
        prijaviliClanovi.add(clan);




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

        TreningDTOPoruka treningDTO=new TreningDTOPoruka(azuriran1.getTrening().getNaziv(),azuriran1.getTrening().getTip(),
                azuriran1.getTrening().getOpis(),azuriran1.getCena(),azuriran1.getTrening().getTrajanje(),
                azuriran1.getPocetak(),azuriran1.getKraj(),0); //ako je vrati 0 sve je ok

        return new ResponseEntity<>(treningDTO,HttpStatus.CREATED);

    }


    @PutMapping(value = "/prijavljeniPut/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TreningDTOPretraga>> odjaviTrening(@PathVariable Long id, @RequestBody ClanID idClan) throws Exception
    {

        Clan clan=this.clanService.findOneID(idClan.getIdClana());
        Termin termin=this.terminService.findOneID(id);

        Set<Termin>prijaviliTermini=clan.getPrijavljeniTermini();
        Set<Clan> prijaviliClanovi=termin.getPrijaviliClanovi();

        prijaviliTermini.remove(termin);
        prijaviliClanovi.remove(clan);
        List<TreningDTOPretraga> terminList=new ArrayList<>();




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




        for(Termin tr:prijaviliTermini){

            TreningDTOPretraga treningDTO=new TreningDTOPretraga(tr.getTrening().getNaziv(),tr.getTrening().getTip(),
                    tr.getTrening().getOpis(),tr.getCena(),tr.getTrening().getTrajanje(),
                    tr.getPocetak(),tr.getKraj(), tr.getId());
            terminList.add(treningDTO);

        }


        return new ResponseEntity<>(terminList,HttpStatus.OK);

    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<TerminDTOTrener> noviTermin(@PathVariable Long id, @RequestBody TerminDTOTrener termin) throws Exception
    {



        Trener trener=this.trenerService.findOne1(id);
        Set<Termin> trenerTermini=trener.getTermini();
        Trening trening=this.treningService.find(termin.getNaziv());

        Sala sala=this.salaService.find(termin.getOznaka());
        Set<Termin> saleTermini=sala.getTermini();

        Termin terminNovi=new Termin();
        terminNovi.setCena(termin.getCena());
        terminNovi.setPocetak(termin.getPocetak());
        terminNovi.setKraj(termin.getKraj());
        terminNovi.setTrener(trener);
        terminNovi.setSala(sala);
        terminNovi.setTrening(trening);

        trenerTermini.add(terminNovi);
        //saleTermini.add(terminNovi);

        Trener noviTR=new Trener();
        noviTR.setId(id);
        noviTR.setTermini(trenerTermini);
        noviTR.setFitness_centri(trener.getFitness_centri());
        noviTR.setActive(trener.isActive());
        noviTR.setEmail(trener.getEmail());
        noviTR.setProsek(trener.getProsek());
        noviTR.setBirthday(trener.getBirthday());
        noviTR.setName(trener.getName());
        noviTR.setPassword(trener.getPassword());
        noviTR.setPhone(trener.getPhone());
        noviTR.setSurname(trener.getSurname());
        noviTR.setUsername(trener.getUsername());

        Sala novaSala=new Sala();
        novaSala.setOznaka(sala.getOznaka());
        novaSala.setKapacitet(sala.getKapacitet());
        novaSala.setFitness_centar(sala.getFitness_centar());
        novaSala.setBroj(sala.getBroj());
        novaSala.setTermini(saleTermini);
        novaSala.setId(sala.getId());

        Sala izmenjena= this.salaService.updateSala(novaSala);
        Trener izmenjen =this.trenerService.updateTrener(noviTR);


        TerminDTOTrener terminNovi1=new TerminDTOTrener();
        terminNovi1.setCena(termin.getCena());
        terminNovi1.setPocetak(termin.getPocetak());
        terminNovi1.setKraj(termin.getKraj());
        terminNovi1.setNaziv(termin.getNaziv());
        terminNovi1.setOznaka(termin.getOznaka());


        return new ResponseEntity<>(terminNovi1,HttpStatus.OK);

    }


    @PutMapping(value = "/putNoviTrening/{id}")
    public ResponseEntity<TerminDTOTrening> noviTrening(@PathVariable Long id, @RequestBody TerminDTOTrening trening) throws Exception
    {
        Trening treningNovi=new Trening();
        treningNovi.setNaziv(trening.getNaziv());
        treningNovi.setOpis(trening.getOpis());
        treningNovi.setTrajanje(trening.getTrajanje());
        treningNovi.setTip(trening.getTip());

        Trening poslat;
        poslat = this.treningService.create(treningNovi);

        Trener trener=this.trenerService.findOne1(id);
        Set<Termin> trenerTermini=trener.getTermini();


        Sala sala=this.salaService.find(trening.getOznaka());
        Set<Termin> saleTermini=sala.getTermini();

        Termin terminNovi=new Termin();
        terminNovi.setCena(trening.getCena());
        terminNovi.setPocetak(trening.getPocetak());
        terminNovi.setKraj(trening.getKraj());
        terminNovi.setTrener(trener);
        terminNovi.setSala(sala);
        terminNovi.setTrening(treningNovi);

        trenerTermini.add(terminNovi);

        Sala novaSala=new Sala();
        novaSala.setOznaka(sala.getOznaka());
        novaSala.setKapacitet(sala.getKapacitet());
        novaSala.setFitness_centar(sala.getFitness_centar());
        novaSala.setBroj(sala.getBroj());
        novaSala.setTermini(saleTermini);
        novaSala.setId(sala.getId());

        Sala izmenjena= this.salaService.updateSala(novaSala);

        Trener noviTR=new Trener();
        noviTR.setId(id);
        noviTR.setTermini(trenerTermini);
        noviTR.setFitness_centri(trener.getFitness_centri());
        noviTR.setActive(trener.isActive());
        noviTR.setEmail(trener.getEmail());
        noviTR.setProsek(trener.getProsek());
        noviTR.setBirthday(trener.getBirthday());
        noviTR.setName(trener.getName());
        noviTR.setPassword(trener.getPassword());
        noviTR.setPhone(trener.getPhone());
        noviTR.setSurname(trener.getSurname());
        noviTR.setUsername(trener.getUsername());


        Trener izmenjen =this.trenerService.updateTrener(noviTR);

        TerminDTOTrening terminNovi1=new TerminDTOTrening();
        terminNovi1.setCena(trening.getCena());
        terminNovi1.setPocetak(trening.getPocetak());
        terminNovi1.setKraj(trening.getKraj());
        terminNovi1.setNaziv(trening.getNaziv());
        terminNovi1.setOznaka(trening.getOznaka());
        terminNovi1.setTip(trening.getTip());
        terminNovi1.setOpis(trening.getOpis());
        terminNovi1.setTrajanje(trening.getTrajanje());

        return new ResponseEntity<>(terminNovi1,HttpStatus.OK);

    }




}
