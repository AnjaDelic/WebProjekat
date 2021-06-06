package projekat.FitnessCentar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekat.FitnessCentar.entity.Administrator;
import projekat.FitnessCentar.service.AdministratorService;

@RestController
@RequestMapping(value = "/api/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;


    @PostMapping (value = "/login",produces = MediaType.APPLICATION_JSON_VALUE) //odgovara na post zahtev
    public ResponseEntity<Administrator> getAdmin(@RequestBody Administrator administrator) {

        Administrator admin = this.administratorService.findOne(administrator.getUsername());

        if(admin==null)
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }


        Administrator administrator1=new Administrator();
       administrator1.setEmail(admin.getEmail());
       administrator1.setActive(admin.isActive());
       administrator1.setBirthday(admin.getBirthday());
       administrator1.setUsername(admin.getUsername());
       administrator1.setSurname(admin.getSurname());
       administrator1.setId(admin.getId());
       administrator1.setName(admin.getName());
       administrator1.setPassword(admin.getPassword());
       administrator1.setPhone(admin.getPhone());




        return new ResponseEntity<>(administrator1, HttpStatus.OK);
    }

    @PostMapping(value = "/post",consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrator> createAdmin(@RequestBody Administrator administrator) throws Exception{

        Administrator admin = new Administrator(administrator.getUsername(),administrator.getPassword(),
                administrator.getName(),administrator.getSurname(),
                administrator.getPhone(),administrator.getEmail(),administrator.getBirthday(),administrator.isActive());


        Administrator noviAdmin= administratorService.createAdmin(admin);

        return new ResponseEntity<>(noviAdmin, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id)
    {
        this.administratorService.deleteAdmin(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //odg 204 uspesno brisanje
    }




}
