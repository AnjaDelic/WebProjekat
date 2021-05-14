package projekat.FitnessCentar.entity;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Administrator implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true) //ne mogu dva korisnika da imaju isto ime i zelimo da svaki korisnik ima username
	private String username;
	
	@Column(nullable=false, unique=true) 
	private String password;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String phone;
	
	@Column(nullable=false, unique=true) //ne mogu dva korisnika da imaju isti email i zelimo da svaki korisnik ima email 
	private String email;
	
	@Column 
	private Date birthday;
	
	
	@Column
	private boolean active;

	public Administrator() {
	
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

	
	
	
}
