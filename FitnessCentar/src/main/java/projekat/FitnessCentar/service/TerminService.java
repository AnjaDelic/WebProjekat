package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.*;
import projekat.FitnessCentar.repository.ClanRepository;
import projekat.FitnessCentar.repository.TerminRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class TerminService {
    @Autowired
    private TerminRepository terminRepository;

    @Autowired
    private ClanRepository clanRepository;
//
    public List<Termin> findAll() {
        List<Termin> termini = this.terminRepository.findAll();
        return termini;
    }

    public Termin findOneID(Long id)
    {

        Termin clan=terminRepository.getOne(id);
        return clan;

    }


    public List<TerminDTO> findSpec(TerminDTO tr){
        List<Termin> terminList = this.terminRepository.findAll();
        List<TerminDTO> treningDTO=new ArrayList<>();
        int br=0;
        int brrijavljenih=0;
        for(Termin termin:terminList){

            for(Clan c:termin.getPrijaviliClanovi()){
                brrijavljenih++;
            }
            br=termin.getSala().getKapacitet()-brrijavljenih;
            //treningDTO svi treninzi
            TerminDTO trening=new TerminDTO(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                    termin.getTrening().getOpis(),termin.getCena(),br,termin.getTrening().getTrajanje(),
                    termin.getPocetak(),termin.getKraj(),termin.getId());
            treningDTO.add(trening);
            //System.out.println(trening);

        }

        List<TerminDTO> poCeni=new ArrayList<>();
        for(TerminDTO termin: treningDTO ){
            if(tr.getCena() >= termin.getCena() ){
                poCeni.add(termin);

            }
        }

        List<TerminDTO> poNazivu=new ArrayList<>();
        if(!tr.getNaziv().equalsIgnoreCase("svi")) {

            for (TerminDTO termin : poCeni) {
                if (tr.getNaziv().equals(termin.getNaziv())) {
                    poNazivu.add(termin);
                }
            }
        } else {

            for (TerminDTO termin : poCeni) {

                poNazivu.add(termin);
            }
        }

           List<TerminDTO> poTipu=new ArrayList<>();
        if(!tr.getTip().equalsIgnoreCase("svi")) {
            for (TerminDTO termin : poNazivu) {
                if (tr.getTip().equals(termin.getTip())) {
                    poTipu.add(termin);
                }
            }
        }
        else {

            for (TerminDTO termin : poNazivu) {

                    poTipu.add(termin);

        }}

           List<TerminDTO> poOpis=new ArrayList<>();
        if(!tr.getOpis().equalsIgnoreCase("svi")) {
            for (TerminDTO termin : poTipu) {
                if (tr.getOpis().equals(termin.getOpis())) {
                    poOpis.add(termin);
                }
            }
        }else {

            for (TerminDTO termin : poTipu) {

                    poOpis.add(termin);

            }
        }

           List<TerminDTO> poTrajanju=new ArrayList<>();
           for(TerminDTO termin:poOpis){
               if(tr.getTrajanje() >= termin.getTrajanje() ){
                   poTrajanju.add(termin);
               }
           }

           List<TerminDTO> poPocetku=new ArrayList<>();
           for(TerminDTO termin:poTrajanju){
               System.out.println("KRITERIJUM: " + tr.getPocetak() + " DATUM: " + termin.getPocetak() + " POSLE: " + termin.getPocetak().after(tr.getPocetak()));
               if(termin.getPocetak().after(tr.getPocetak()) ){ //zelimo treninge posle ovog
                   poPocetku.add(termin);
               }
           }

           List<TerminDTO> poKraj=new ArrayList<>();
           for(TerminDTO termin:poTrajanju){
               if(termin.getKraj().before(tr.getKraj()) ){
                   poKraj.add(termin);
               }
           }



       return poKraj;
    }


    public Termin updateTermin(Termin termin) throws Exception {

        Termin ispravljenT = this.terminRepository.getOne(termin.getId());

        if (ispravljenT == null) //provera da li u bazi postoji takav
        {
            throw new Exception("Ne postoji Clan");
        }


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

        //cuvanje u bazu
        Termin izmenjen=this.terminRepository.save(ispravljenT);
        return izmenjen;
    }


    public Termin izmeni(Clan clan,Termin termin) throws Exception {

        Termin ispravljenT = this.terminRepository.getOne(termin.getId());

        if (ispravljenT == null) //provera da li u bazi postoji takav
        {
            throw new Exception("Ne postoji Clan");
        }

        /*Set<Termin> clanSet=clan.getPrijavljeniTermini();
        Set<Clan> terminSet=termin.getPrijaviliClanovi();

        for(Termin tr:clanSet){
            if(tr.getId().equals(ispravljenT.getId())){

            clanSet.remove(tr);

            }

        }
        for(Clan c:terminSet){
            if(c.getId().equals(ispravljenT.getId())){

                terminSet.remove(c);

            }

        }


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
        ispravljen.setPrijavljeniTermini(clanSet);
        ispravljen.setEmail(clan.getEmail());

        Termin ostaje=new Termin();

        ostaje.setOcene(termin.getOcene());
        ostaje.setOceniliClanovi(termin.getOceniliClanovi());
        ostaje.setOdradiliClanovi(termin.getOdradiliClanovi());
        ostaje.setSala(termin.getSala());
        ostaje.setTrener(termin.getTrener());
        ostaje.setPrijaviliClanovi(terminSet);
        ostaje.setCena(termin.getCena());
        ostaje.setKraj(termin.getKraj());
        ostaje.setPocetak(termin.getPocetak());
        ostaje.setTrening(termin.getTrening());


        //cuvanje u bazu
        Clan izmenjen=this.clanRepository.save(ispravljen);
        Termin izmenjen1=this.terminRepository.save(ostaje);*/
        return ispravljenT;
    }

}
