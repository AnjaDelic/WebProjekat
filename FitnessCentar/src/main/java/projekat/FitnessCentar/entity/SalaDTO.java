package projekat.FitnessCentar.entity;

public class SalaDTO {

    String oznaka;
    int kapacitet;

    Long id;

    public SalaDTO() {
    }

    public SalaDTO(String oznaka, int kapacitet,  Long id) {

        this.oznaka = oznaka;
        this.kapacitet = kapacitet;

        this.id = id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }
}
