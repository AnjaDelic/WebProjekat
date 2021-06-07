package projekat.FitnessCentar.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import javax.persistence.*;




@Entity

public class Trener implements Serializable {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true) //ne mogu dva korisnika da imaju isto ime i zelimo da svaki korisnik ima username
	private String username;
	
	@Column(nullable=false) 
	private String password;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column(unique=true)
	private String phone;
	
	@Column(nullable=false, unique=true) //ne mogu dva korisnika da imaju isti email i zelimo da svaki korisnik ima email 
	private String email;
	
	@Column 
	private Date birthday;

	@Column
	private boolean active;
	
	@Column
	private double prosek;
	

	@OneToMany(mappedBy="trener", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	private Set<Termin> termini= new HashSet<Termin>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	 private FC fitness_centri;
	
	public Trener() {
		prosek=0;
		
	}

	

	



	public Trener(String username, String password, String name, String surname, String phone, String email,
			Date birthday, boolean active, double prosek) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.active = active;
		this.prosek = prosek;
	}







	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}



	public double getProsek() {
		return prosek;
	}



	public void setProsek(double prosek) {
		this.prosek = prosek;
	}


	
	


}
