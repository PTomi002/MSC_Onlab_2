package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import hu.bme.msc.onlab.validator.annotation.Email;
import hu.bme.msc.onlab.validator.annotation.Password;
import hu.bme.msc.onlab.validator.annotation.StringField;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = -5280401467697527376L;

	@Id
	@Column(name = "USER_ID", nullable = false, length = 15)
	@NotNull(message = "Username can not be null!")
	@Size(min = 5, max = 15, message = "Username length should be between 5 and 15 character!")
	@StringField(message = "Username should contain valid characters!")
	private String usernameId;

	@Column(name = "FIRST_NAME", nullable = false, length = 15)
	@NotNull(message = "First name can not be null!")
	@Size(min = 5, max = 15, message = "First name length should be between 5 and 15 character!")
	@StringField(message = "First name should contain valid characters!")
	private String firstname;

	@Column(name = "LAST_NAME", nullable = false, length = 15)
	@NotNull(message = "Last name can not be null!")
	@Size(min = 5, max = 15, message = "Last name length should be between 5 and 15 character!")
	@StringField(message = "Last name should contain valid characters!")
	private String lastname;

	@Column(name = "EMAIL", nullable = false, length = 15)
	@NotNull(message = "E-mail can not be null!")
	@Email(message = "E-mail is not valid!")
	private String email;

	@Transient
	@NotNull(message = "Password can not be null!")
	@Size(min = 5, max = 15, message = "Password length should be between 5 and 15 character!")
	@Password(message = "Password is invalid!")
	private transient String password;

	// ================================================
	// Not validated attributes
	
	// is not validated because generated in server side
	@Column(name = "REGISTRATION_DATE", nullable = false)
	private Date registrationDate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "actualUser")
	private List<TroubleReport> trList;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<ModificationHandlingOffice> mhos;
	
	public User() {
		// Needed for Hibernate instantiation.
	}

	public String getUsernameId() {
		return usernameId;
	}

	public List<ModificationHandlingOffice> getMhos() {
		return mhos;
	}

	public void setMhos(List<ModificationHandlingOffice> mhos) {
		this.mhos = mhos;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistratinDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<TroubleReport> getTrList() {
		return trList;
	}

	public void setTrList(List<TroubleReport> trList) {
		this.trList = trList;
	}

	@Override
	public String toString() {
		return "User [usernameId=" + usernameId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", registration_date=" + registrationDate + "]";
	}
}
