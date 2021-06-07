package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.Clan;
import projekat.FitnessCentar.entity.FC;
import projekat.FitnessCentar.service.ClanService;

@Controller
@RequestMapping(value = "/api/Clan")
public class ClanController {

    @Autowired
    private ClanService clanService;

    //dodavanje Clana
    @PostMapping(value = "/post", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clan> createClan(@RequestBody ClanDTO clan) throws Exception{
        ClanDTO cl=new ClanDTO(clan.getName(),clan.getSurname(),clan.getUsername(),clan.getPassword(),clan.getEmail(),
                clan.getPhone(),clan.getBirthday());

        Clan clan1=new Clan();
        clan1.setEmail(cl.getEmail());
        clan1.setBirthday(cl.getBirthday());
        clan1.setUsername(cl.getUsername());
        clan1.setSurname(cl.getSurname());
        clan1.setName(cl.getName());
        clan1.setPassword(cl.getPassword());
        clan1.setPhone(cl.getPhone());
        clan1.setActive(true);


        Clan noviClan= clanService.createClan(clan1);

        return new ResponseEntity<>(noviClan,HttpStatus.CREATED);
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
    public ResponseEntity<Clan> updateClan(@PathVariable Long id, @RequestBody Clan c) throws Exception
    {
        Clan clan = new Clan(c.getUsername(),c.getPassword(),c.getName(),c.getSurname(),c.getPhone(),c.getEmail(),c.getBirthday(),c.isActive());


        clan.setId(id); //proslednjen nam je njegov id

        Clan izmenjenclan= clanService.updateClan(clan);

        return new ResponseEntity<>(izmenjenclan,HttpStatus.OK);

    }
    @PostMapping (value = "/login",produces = MediaType.APPLICATION_JSON_VALUE) //odgovara na post zahtev
    public ResponseEntity<Clan> getClan(@RequestBody Clan clan) throws Exception {

        Clan cl = this.clanService.findOne(clan.getUsername());

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
}
