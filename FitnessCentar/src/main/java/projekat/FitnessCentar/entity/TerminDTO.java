package projekat.FitnessCentar.entity;

import java.util.Date;

public class TerminDTO {

    String naziv,tip,opis;
    int cena,br;
    double trajanje;
    Date  pocetak,kraj ;
    Long id;

    public TerminDTO() {
    }

    public TerminDTO(String naziv, String tip, String opis, int cena, int br, double trajanje, Date pocetak, Date kraj, Long id) {
        this.naziv = naziv;
        this.tip = tip;
        this.opis = opis;
        this.cena = cena;
        this.br = br;
        this.trajanje = trajanje;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.id = id;
    }

    public int getBr() {
        return br;
    }

    public void setBr(int vrati) {
        this.br = vrati;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public double getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(double trajanje) {
        this.trajanje = trajanje;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    @Override
    public String toString() {
        return "TreningDTO{" +
                "naziv='" + naziv + '\'' +
                ", tip='" + tip + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                ", trajanje=" + trajanje +
                ", pocetak=" + pocetak +
                ", kraj=" + kraj +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

