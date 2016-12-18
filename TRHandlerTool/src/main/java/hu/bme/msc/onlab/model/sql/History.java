package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History implements Serializable {

	private static final long serialVersionUID = 6801914901753169256L;

	@Id
	@Column(name = "HISTORY_ID", nullable = false)
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long historyId;

	@ManyToOne
	@JoinColumn(name = "TR_ID", nullable = false)
	private TroubleReport troubleReport;

	@Column(name = "USERNAME_ID", length = 15, nullable = false)
	private String usernameId;

	@Column(name = "WHEN_HAPPENED", nullable = false)
	private Date when;

	@Column(name = "OLD_STATE", nullable = true)
	@Enumerated(EnumType.ORDINAL)
	private TroubleReportStage oldState;

	@Column(name = "NEW_STATE", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TroubleReportStage newState;
	
	public History() {
		// Needed for Hibernate instantiation.
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
