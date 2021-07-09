package projekat.FitnessCentar.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

public class Sala implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int kapacitet;
	
	@Column
	private String oznaka;
	

	
	@OneToMany(mappedBy="sala", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Termin>termini= new HashSet<Termin>();
	
	@ManyToOne( fetch= FetchType.EAGER)
	private FC fitness_centar;
	
	
	public Sala() {

	}

	public Sala(int kapacitet, String oznaka) {
		this.kapacitet = kapacitet;
		this.oznaka = oznaka;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public int getKapacitet() {
		return kapacitet;
	}



	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}



	public String getOznaka() {
		return oznaka;
	}



	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}



	public Set<Termin> getTermini() {
		return termini;
	}



	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}




	public FC getFitness_centar() {
		return fitness_centar;
	}

	public void setFitness_centar(FC fitness_centar) {
		this.fitness_centar = fitness_centar;
	}
}
