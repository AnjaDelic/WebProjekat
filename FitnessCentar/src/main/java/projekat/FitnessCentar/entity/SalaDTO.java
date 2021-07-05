package projekat.FitnessCentar.entity;

public class SalaDTO {

    String oznaka;
    int kapacitet,broj;

    Long id;

    public SalaDTO(String oznaka, int kapacitet, int broj, Long id) {

        this.oznaka = oznaka;
        this.kapacitet = kapacitet;
        this.broj = broj;
        this.id = id;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
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
