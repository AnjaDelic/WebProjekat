package projekat.FitnessCentar.entity;

public class OcenaDTO {

    double ocena;
    Long idClan,idTermin;

    public OcenaDTO(double ocena, Long idClan, Long idTermin) {
        this.ocena = ocena;
        this.idClan = idClan;
        this.idTermin = idTermin;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public Long getIdClan() {
        return idClan;
    }

    public void setIdClan(Long idClan) {
        this.idClan = idClan;
    }

    public Long getIdTermin() {
        return idTermin;
    }

    public void setIdTermin(Long idTermin) {
        this.idTermin = idTermin;
    }
}
