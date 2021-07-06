package projekat.FitnessCentar.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="TERMIN")
public class Termin  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="termin", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Ocena>ocene= new HashSet<Ocena>();
	
	@ManyToOne( fetch= FetchType.EAGER)
	private Trener trener;
	
	@ManyToOne( fetch= FetchType.EAGER)
	private Trening trening;
	
	@ManyToOne( fetch= FetchType.EAGER)
	private Sala sala;
	

    @ManyToMany
    @JoinTable(name = "prijavljeniTermini",
            joinColumns = @JoinColumn(name = "terminID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "clanID", referencedColumnName = "id"))
    	private Set<Clan> prijaviliClanovi = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "odradjeniTermini",
            joinColumns = @JoinColumn(name = "terminID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "clanID", referencedColumnName = "id"))
    private Set<Clan> odradiliClanovi = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "ocenjeniTermini",
			joinColumns = @JoinColumn(name = "terminID", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "clanID", referencedColumnName = "id"))
	private Set<Clan> oceniliClanovi = new HashSet<>();
	
	@Column
	private Date pocetak;
	
	
	@Column
	private Date kraj;
	
	@Column(nullable=false)
	private int cena;
	
	

	public Termin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Trener getTrener() {
		return trener;
	}

	public void setTrener(Trener trener) {
		this.trener = trener;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<Clan> getPrijaviliClanovi() {
		return prijaviliClanovi;
	}

	public void setPrijaviliClanovi(Set<Clan> prijaviliClanovi) {
		this.prijaviliClanovi = prijaviliClanovi;
	}

	public Set<Clan> getOdradiliClanovi() {
		return odradiliClanovi;
	}

	public void setOdradiliClanovi(Set<Clan> odradiliClanovi) {
		this.odradiliClanovi = odradiliClanovi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}


	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public Set<Clan> getOceniliClanovi() {
		return oceniliClanovi;
	}

	public void setOceniliClanovi(Set<Clan> oceniliClanovi) {
		this.oceniliClanovi = oceniliClanovi;
	}
}
