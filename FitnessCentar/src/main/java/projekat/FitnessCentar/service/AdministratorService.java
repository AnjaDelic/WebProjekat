package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.controller.AdministratorController;
import projekat.FitnessCentar.entity.Administrator;
import projekat.FitnessCentar.entity.FC;
import projekat.FitnessCentar.repository.AdministratorRepository;


@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

   public Administrator createAdmin(Administrator admin) throws Exception {
       if (admin.getId()!=null)
       {
            throw new Exception("ID must be null");
       }
       Administrator noviAdmin=this.administratorRepository.save(admin);
       return  noviAdmin;
   }

   public void deleteAdmin(Long id)
   {
       this.administratorRepository.deleteById(id);
   }


    public Administrator findOne(String username)
    {

        Administrator administrator=administratorRepository.findAdministratorByUsername(username);
        return administrator;

    }

    public Administrator findByUsernameAndPassword(String username,String password){
        Administrator administrator=administratorRepository.findAdministratorByUsernameAndPassword(username,password);
        return administrator;
     }
}
