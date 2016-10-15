package sql.model.onlab.msc.bme.hu;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import annotation.validator.onlab.msc.bme.hu.StringField;

@Entity
@Table(name = "user")
public class User implements Serializable{
	private static final long serialVersionUID = -5280401467697527376L;

	@Id
	@Column(name = "username", nullable = false)
	@NotNull @Size(min = 5, max = 15) @StringField
	private String username;
	
	@Column(name = "firstname", nullable = false)
	@NotNull @Size(min = 5, max = 15) @StringField
	private String firstname;
	
	@Column(name = "lastname", nullable = false)
	@NotNull @Size(min = 5, max = 15) @StringField
	private String lastname;
	
	@Column(name = "registration_date", nullable = false)
	private Date registration_date;
	
	@Column(name = "email", nullable = false)
	@NotNull @Email
	private String email;
	
	@Transient
	@NotNull @Size(min = 5, max = 15) @StringField
	private String password;
	
	public User() {
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", registration_date=" + registration_date + ", email=" + email + "]";
	}
}
