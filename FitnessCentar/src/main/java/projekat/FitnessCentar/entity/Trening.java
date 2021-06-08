package projekat.FitnessCentar.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import javax.persistence.*;

@Entity

public class Trening {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false,unique=true)
	private String naziv;
	
	@Column
	private String opis;
	@Column(nullable=false)
	private double trajanje;
	
	@Column
	private String tip;
	
	
	
	@OneToMany(mappedBy="trening", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Termin>termini= new HashSet<Termin>();
	
	
	
	public Trening() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}
