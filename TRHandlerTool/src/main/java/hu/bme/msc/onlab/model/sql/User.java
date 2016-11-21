package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import hu.bme.msc.onlab.validator.annotation.Email;
import hu.bme.msc.onlab.validator.annotation.Password;
import hu.bme.msc.onlab.validator.annotation.StringField;

public class User implements Serializable {
	private static final long serialVersionUID = -5280401467697527376L;

	@NotNull(message = "Username can not be null!")
	@Size(min = 5, max = 15, message = "Username length should be between 5 and 15 character!")
	@StringField(message = "Username should contain valid characters!")
	private String usernameId;

	@NotNull(message = "First name can not be null!")
	@Size(min = 5, max = 15, message = "First name length should be between 5 and 15 character!")
	@StringField(message = "First name should contain valid characters!")
	private String firstname;

	@NotNull(message = "Last name can not be null!")
	@Size(min = 5, max = 15, message = "Last name length should be between 5 and 15 character!")
	@StringField(message = "Last name should contain valid characters!")
	private String lastname;

	@NotNull(message = "E-mail can not be null!")
	@Email(message = "E-mail is not valid!")
	private String email;

	@NotNull(message = "Password can not be null!")
	@Size(min = 5, max = 15, message = "Password length should be between 5 and 15 character!")
	@Password(message = "Password is invalid!")
	private String password;

	// ================================================
	// Not validated attributes
	
	// is not validated because generated in server side
	private Date registration_date;
	
	private List<TroubleReport> trList;
	
	public User() {
	}

	public String getUsernameId() {
		return usernameId;
	}


	public void setUsernameId(String usernameId) {
		this.usernameId = usernameId;
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


	public Date getRegistration_date() {
		return registration_date;
	}


	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}


	public List<TroubleReport> getTrList() {
		return trList;
	}


	public void setTrList(List<TroubleReport> trList) {
		this.trList = trList;
	}


	@Override
	public String toString() {
		return "User [username=" + usernameId + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", registration_date=" + registration_date + "]";
	}
}
