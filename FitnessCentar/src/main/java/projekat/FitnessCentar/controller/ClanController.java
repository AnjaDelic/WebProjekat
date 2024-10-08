package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.service.ClanService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/api/Clan")
public class ClanController {

    @Autowired
    private ClanService clanService;

    //dodavanje Clana
    @PostMapping(value = "/post", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClanDTO> createClan(@RequestBody ClanDTO clanDTO) throws Exception{
        ClanDTO cl=new ClanDTO(clanDTO.getName(),clanDTO.getSurname(),clanDTO.getUsername(),clanDTO.getPassword(),clanDTO.getEmail(),
                clanDTO.getPhone(),clanDTO.getBirthday(),clanDTO.getVrati(),clanDTO.isActive());

        List<Clan> clanList = clanService.findAll();
        for(Clan clan : clanList) {
            if(clan.getUsername().equalsIgnoreCase(cl.getUsername())) {
                ClanDTO vrati = new ClanDTO("","","","","","",clan.getBirthday(),1,false);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }
            if(clan.getEmail().equalsIgnoreCase(cl.getEmail())) {
                ClanDTO vrati = new ClanDTO("","","","","","",clan.getBirthday(),2,false);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }
            if(clan.getPhone().equalsIgnoreCase(cl.getPhone())) {
                ClanDTO vrati = new ClanDTO("","","","","","",clan.getBirthday(),3,false);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }

        }

        ClanDTO clan1=new ClanDTO();
        clan1.setEmail(cl.getEmail());
        clan1.setBirthday(cl.getBirthday());
        clan1.setUsername(cl.getUsername());
        clan1.setSurname(cl.getSurname());
        clan1.setName(cl.getName());
        clan1.setPassword(cl.getPassword());
        clan1.setPhone(cl.getPhone());
         clan1.setVrati(cl.getVrati());
        clan1.setActive(true);

        //clana cuvam
        Clan clan2=new Clan();
        clan2.setEmail(cl.getEmail());
        clan2.setBirthday(cl.getBirthday());
        clan2.setUsername(cl.getUsername());
        clan2.setSurname(cl.getSurname());
        clan2.setName(cl.getName());
        clan2.setPassword(cl.getPassword());
        clan2.setPhone(cl.getPhone());
        clan2.setActive(true);

        Clan noviClan= clanService.createClan(clan2);

        return new ResponseEntity<ClanDTO>(clan1,HttpStatus.CREATED);
    }

    //brisanje clana
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteClan(@PathVariable Long id)
    {
        this.clanService.deleteClan(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //odg 204 uspesno brisanje
    }

    //izmena clan
    @PutMapping(value = "/put/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClanDTO> updateClan(@PathVariable Long id, @RequestBody ClanDTO clanDTO) throws Exception
    {
        ClanDTO cl=new ClanDTO(clanDTO.getName(),clanDTO.getSurname(),clanDTO.getUsername(),clanDTO.getPassword(),clanDTO.getEmail(),
                clanDTO.getPhone(),clanDTO.getBirthday(),clanDTO.getVrati(),clanDTO.isActive());

        List<Clan> clanList = clanService.findAll();
        for(Clan clan : clanList) {
            if(clan.getUsername().equalsIgnoreCase(cl.getUsername())) {
                ClanDTO vrati = new ClanDTO("","","","","","",clan.getBirthday(),1,false);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }
            if(clan.getEmail().equalsIgnoreCase(cl.getEmail())) {
                ClanDTO vrati = new ClanDTO("","","","","","",clan.getBirthday(),2,false);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }
            if(clan.getPhone().equalsIgnoreCase(cl.getPhone())) {
                ClanDTO vrati = new ClanDTO("","","","","","",clan.getBirthday(),3,false);
                return new ResponseEntity<>(vrati, HttpStatus.CREATED);
            }

        }

        Clan clan2=new Clan();
        clan2.setId(id);
        clan2.setEmail(cl.getEmail());
        clan2.setBirthday(cl.getBirthday());
        clan2.setUsername(cl.getUsername());
        clan2.setSurname(cl.getSurname());
        clan2.setName(cl.getName());
        clan2.setPassword(cl.getPassword());
        clan2.setPhone(cl.getPhone());
        clan2.setActive(true);
        Clan izmenjenclan= clanService.updateClan(clan2);

        ClanDTO clan1=new ClanDTO(izmenjenclan.getName(),izmenjenclan.getSurname(),izmenjenclan.getUsername(), izmenjenclan.getPassword(), izmenjenclan.getEmail(), izmenjenclan.getPhone(),
                izmenjenclan.getBirthday(),cl.getVrati(),true);


        return new ResponseEntity<>(clan1,HttpStatus.OK);

    }
    @PostMapping (value = "/login",produces = MediaType.APPLICATION_JSON_VALUE) //odgovara na post zahtev
    public ResponseEntity<Clan> getClan(@RequestBody Clan clan) throws Exception {

        Clan cl = this.clanService.findByUsernameAndPassword(clan.getUsername(),clan.getPassword());

        if(cl==null)
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }


        Clan clan1=new Clan();
        clan1.setEmail(cl.getEmail());
        clan1.setActive(cl.isActive());
        clan1.setBirthday(cl.getBirthday());
        clan1.setUsername(cl.getUsername());
        clan1.setSurname(cl.getSurname());
        clan1.setId(cl.getId());
        clan1.setName(cl.getName());
        clan1.setPassword(cl.getPassword());
        clan1.setPhone(cl.getPhone());


        return new ResponseEntity<>(clan1, HttpStatus.OK);
    }



    @GetMapping (value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clan> getClan(@PathVariable Long id) throws Exception {

        Clan clan = this.clanService.findOneID(id);

        Clan povratni= new Clan();

        povratni.setUsername(clan.getUsername());
        povratni.setEmail(clan.getEmail());
        povratni.setName(clan.getName());
        povratni.setPassword(clan.getPassword());
        povratni.setActive( clan.isActive());
        povratni.setSurname(clan.getSurname());
        povratni.setPhone(clan.getPhone());
        povratni.setBirthday(clan.getBirthday());
        povratni.setId(id);






        return new ResponseEntity<>(povratni, HttpStatus.OK);
    }


}
