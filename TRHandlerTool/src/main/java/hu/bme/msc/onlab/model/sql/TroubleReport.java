package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;

public class TroubleReport implements Serializable{

	private static final long serialVersionUID = 4952212837033940343L;

	private long troubleReportId;
	
	private User user;
	
	private String answer;
	
	private String observation;
	
	public TroubleReport() {
	}

	public long getTroubleReportId() {
		return troubleReportId;
	}

	public void setTroubleReportId(long troubleReportId) {
		this.troubleReportId = troubleReportId;
	}

	public User getUser() {
		return user;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TroubleReport [id=" + troubleReportId  + "]";
	}
}
