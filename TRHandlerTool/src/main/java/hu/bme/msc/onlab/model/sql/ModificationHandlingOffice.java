package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mho")
public class ModificationHandlingOffice implements Serializable {

	private static final long serialVersionUID = 7271699648011508705L;

	@Id
	@Column(name = "MHO_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mhoId;

	@Column(name = "NAME", length = 15, nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_mho", joinColumns = {
			@JoinColumn(name = "MHO_ID", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "USER_ID", nullable = false) })
	private List<User> users;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "mho")
	private List<TroubleReport> trs;

	public ModificationHandlingOffice() {
	}

	public int getMhoId() {
		return mhoId;
	}

	public void setMhoId(int mhoId) {
		this.mhoId = mhoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<TroubleReport> getTrs() {
		return trs;
	}

	public void setTrs(List<TroubleReport> trs) {
		this.trs = trs;
	}

	@Override
	public String toString() {
		return "ModificationHandlingOffice [mhoId=" + mhoId + ", name=" + name + "]";
	}

}
