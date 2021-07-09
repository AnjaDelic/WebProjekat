package projekat.FitnessCentar.entity;

import java.util.Date;

public class TerminDTORaspored {

    String naziv,vrati;
    Long idFC,idTermina;

    public TerminDTORaspored() {
    }

    public String getVrati() {
        return vrati;
    }

    public void setVrati(String vrati) {
        this.vrati = vrati;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getIdFC() {
        return idFC;
    }

    public void setIdFC(Long idFC) {
        this.idFC = idFC;
    }

    public Long getIdTermina() {
        return idTermina;
    }

    public void setIdTermina(Long idTermina) {
        this.idTermina = idTermina;
    }
}
