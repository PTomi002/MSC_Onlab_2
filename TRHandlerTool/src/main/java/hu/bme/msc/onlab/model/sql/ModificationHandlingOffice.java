package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.List;

public class ModificationHandlingOffice implements Serializable {

	private static final long serialVersionUID = 7271699648011508705L;
	
	private int mhoId;
	
	private String name;
	
	private List<User> users;
	
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
		return "ModificationHandlingOffice [mhoId=" + mhoId + ", name=" + name + ", users=" + users + ", trs=" + trs
				+ "]";
	}
	
	
}
