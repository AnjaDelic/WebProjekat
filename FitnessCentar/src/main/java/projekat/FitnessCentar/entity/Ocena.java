package projekat.FitnessCentar.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity

public class Ocena implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private double ocena;

	
	@ManyToOne(fetch = FetchType.EAGER)
	private Clan clan;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Termin termin;
	
	
	
	public Ocena() {
		
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
	
	
	
	
	

}
