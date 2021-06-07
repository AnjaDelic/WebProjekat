package projekat.FitnessCentar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;



@Entity

public class FC implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public FC() {
		// TODO Auto-generated constructor stub
	}

	public FC(String naziv, String adresa, String broj, String email) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.broj = broj;
		this.email = email;
	}

	@Column(nullable=false)
	private String naziv;
	
	@Column(nullable=false)
	private String adresa;
	
	@Column(nullable=false,unique=true)
	private String broj;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	@OneToMany(mappedBy="fitness_centri", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Trener>treneri= new HashSet<Trener>();
	
	@OneToMany(mappedBy="fitness_centar", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Sala>sale= new HashSet<Sala>();

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

	

	


	
	
	
	
	
	
	
	
}
