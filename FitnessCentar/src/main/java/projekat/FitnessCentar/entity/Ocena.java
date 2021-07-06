package projekat.FitnessCentar.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity

public class Ocena implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private double ocena;

	
	@ManyToOne(fetch = FetchType.EAGER)
	private Clan clan;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Termin termin;
	
	
	
	public Ocena() {
		
	}

	public Ocena( Clan clan, Termin termin) {


		this.clan = clan;
		this.termin = termin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}
}
