package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.Date;

public class History implements Serializable {

	private static final long serialVersionUID = 6801914901753169256L;

	private Long historyId;

	private TroubleReport troubleReport;

	private String usernameId;

	private Date when;

	private TroubleReportStage oldState;

	private TroubleReportStage newState;

	public History() {
	}

	public TroubleReport getTroubleReport() {
		return troubleReport;
	}

	public void setTroubleReport(TroubleReport troubleReport) {
		this.troubleReport = troubleReport;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public String getUsernameId() {
		return usernameId;
	}

	public void setUsernameId(String usernameId) {
		this.usernameId = usernameId;
	}

	public Date getWhen() {
		return when;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

	public TroubleReportStage getOldState() {
		return oldState;
	}

	public void setOldState(TroubleReportStage oldState) {
		this.oldState = oldState;
	}

	public TroubleReportStage getNewState() {
		return newState;
	}

	public void setNewState(TroubleReportStage newState) {
		this.newState = newState;
	}

	@Override
	public String toString() {
		return "History [historyId=" + historyId + ", troubleReport=" + troubleReport + ", usernameId=" + usernameId
				+ ", when=" + when + ", oldState=" + oldState + ", newState=" + newState + "]";
	}

}
