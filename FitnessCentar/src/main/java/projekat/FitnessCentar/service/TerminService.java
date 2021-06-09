package projekat.FitnessCentar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekat.FitnessCentar.entity.Termin;
import projekat.FitnessCentar.entity.TrenerDTO;
import projekat.FitnessCentar.entity.TreningDTO;
import projekat.FitnessCentar.entity.TreningDTOPretraga;
import projekat.FitnessCentar.repository.TerminRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TerminService {
    @Autowired
    private TerminRepository terminRepository;
//
    public List<Termin> findAll() {
        List<Termin> termini = this.terminRepository.findAll();
        return termini;
    }


    public List<TreningDTO> findSpec(TreningDTO tr){
        List<Termin> terminList = this.terminRepository.findAll();
        List<TreningDTO> treningDTO=new ArrayList<>();

        for(Termin termin:terminList){
            //treningDTO svi treninzi
            TreningDTO trening=new TreningDTO(termin.getTrening().getNaziv(),termin.getTrening().getTip(),
                    termin.getTrening().getOpis(),termin.getCena(),termin.getTrening().getTrajanje(),
                    tr.getPocetak(),tr.getKraj());
            treningDTO.add(trening);

        }

        List<TreningDTO> poCeni=new ArrayList<>();
        for(TreningDTO termin: treningDTO ){
            if(tr.getCena() >= termin.getCena() ){
                poCeni.add(termin);
            }
        }

        List<TreningDTO> poNazivu=new ArrayList<>();
        if(tr.getNaziv()!="svi") {
            for (TreningDTO termin : poCeni) {
                if (tr.getNaziv().equals(termin.getNaziv())) {
                    poNazivu.add(termin);
                }
            }
        }

           List<TreningDTO> poTipu=new ArrayList<>();
        if(tr.getTip()!="svi") {
            for (TreningDTO termin : poNazivu) {
                if (tr.getTip().equals(termin.getTip())) {
                    poTipu.add(termin);
                }
            }
        }

           List<TreningDTO> poOpis=new ArrayList<>();
        if(tr.getOpis()!="svi") {
            for (TreningDTO termin : poTipu) {
                if (tr.getOpis().equals(termin.getOpis())) {
                    poOpis.add(termin);
                }
            }
        }

           List<TreningDTO> poTrajanju=new ArrayList<>();
           for(TreningDTO termin:poOpis){
               if(tr.getTrajanje() >= termin.getTrajanje() ){
                   poTrajanju.add(termin);
               }
           }

           List<TreningDTO> poPocetku=new ArrayList<>();
           for(TreningDTO termin:poTrajanju){
               if(tr.getPocetak().after(termin.getPocetak()) ){ //zelimo treninge posle ovog
                   poPocetku.add(termin);
               }
           }

           List<TreningDTO> poKraj=new ArrayList<>();
           for(TreningDTO termin:poPocetku){
               if(tr.getKraj().before(termin.getKraj()) ){
                   poKraj.add(termin);
               }
           }



       return poKraj;
    }

}
