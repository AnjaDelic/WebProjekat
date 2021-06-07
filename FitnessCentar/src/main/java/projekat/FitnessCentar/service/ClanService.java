package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.Clan;
import projekat.FitnessCentar.repository.ClanRepository;

@Service
public class ClanService {

    @Autowired
    private ClanRepository clanRepository;


    public Clan createClan(Clan clan) throws Exception {
        if (clan.getId()!=null)
        {
            throw new Exception("ID must be null");
        }
        Clan noviCl=this.clanRepository.save(clan);
        return  noviCl;
    }

    public void deleteClan(Long id)
    {
        this.clanRepository.deleteById(id);
    }

    public Clan updateClan(Clan clan) throws Exception {

        Clan probaClan = this.clanRepository.getOne(clan.getId()); //dobavljamo tog zaposlenog

        if(probaClan==null) //provera da li u bazi postoji takav fc
        {
            throw new Exception("Ne postoji Clan");
        }
        //promena vrednosti
        probaClan.setActive(clan.isActive());
        probaClan.setBirthday(clan.getBirthday());
        probaClan.setEmail(clan.getEmail());
        probaClan.setName(clan.getName());
        probaClan.setPassword(clan.getPassword());
        probaClan.setPhone(clan.getPhone());
        probaClan.setSurname(clan.getSurname());
        probaClan.setUsername(clan.getUsername());

        //cuvanje u bazu
        Clan izmenjenClan=this.clanRepository.save(probaClan);
        return izmenjenClan;


    }

    public Clan findOne(String username)
    {

        Clan clan=clanRepository.findClanByUsername(username);
        return clan;

    }

}
