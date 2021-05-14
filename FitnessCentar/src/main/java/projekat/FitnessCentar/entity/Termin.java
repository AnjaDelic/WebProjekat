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
	
	@Column
	private Date pocetak;
	
	
	@Column
	private Date kraj;
	
	@Column(nullable=false)
	private double cena;
	
	

	public Termin() {
		super();
		// TODO Auto-generated constructor stub
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}





	
	
	
}
