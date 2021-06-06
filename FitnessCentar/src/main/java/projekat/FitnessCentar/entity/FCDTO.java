package projekat.FitnessCentar.entity;

public class FCDTO {

    String naziv,adresa,broj,email;
    Long id;

    public FCDTO(String naziv, String adresa, String broj, String email, Long id) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.broj = broj;
        this.email = email;
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
