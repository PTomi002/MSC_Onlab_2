package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.List;

public class TroubleReport implements Serializable {

	private static final long serialVersionUID = 4952212837033940343L;

	private Long troubleReportId;

	private User actualUser;

	private String answerField;

	private String observationField;

	private Product plannedReleaseVersion;

	private Product releaseVersion;

	private Product issuedFor;

	private List<History> history;

	private TroubleReportStage stage;
	
	private Priority priority;
	
	private ModificationHandlingOffice mho;

	public TroubleReport() {
	}

	public ModificationHandlingOffice getMho() {
		return mho;
	}

	public void setMho(ModificationHandlingOffice mho) {
		this.mho = mho;
	}

	public Long getTroubleReportId() {
		return troubleReportId;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setTroubleReportId(Long troubleReportId) {
		this.troubleReportId = troubleReportId;
	}

	public User getActualUser() {
		return actualUser;
	}

	public void setActualUser(User actualUser) {
		this.actualUser = actualUser;
	}

	public String getAnswerField() {
		return answerField;
	}

	public void setAnswerField(String answerField) {
		this.answerField = answerField;
	}

	public String getObservationField() {
		return observationField;
	}

	public void setObservationField(String observationField) {
		this.observationField = observationField;
	}

	public Product getPlannedReleaseVersion() {
		return plannedReleaseVersion;
	}

	public void setPlannedReleaseVersion(Product plannedReleaseVersion) {
		this.plannedReleaseVersion = plannedReleaseVersion;
	}

	public Product getReleaseVersion() {
		return releaseVersion;
	}

	public void setReleaseVersion(Product releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

	public Product getIssuedFor() {
		return issuedFor;
	}

	public void setIssuedFor(Product issuedFor) {
		this.issuedFor = issuedFor;
	}

	public List<History> getHistory() {
		return history;
	}

	public void setHistory(List<History> history) {
		this.history = history;
	}

	public TroubleReportStage getStage() {
		return stage;
	}

	public void setStage(TroubleReportStage stage) {
		this.stage = stage;
	}

	@Override
	public String toString() {
		return "TroubleReport [troubleReportId=" + troubleReportId + ", actualUser=" + actualUser + ", answerField="
				+ answerField + ", observationField=" + observationField + ", plannedReleaseVersion="
				+ plannedReleaseVersion + ", releaseVersion=" + releaseVersion + ", issuedFor=" + issuedFor
				+ ", history=" + history + ", stage=" + stage + ", priority=" + priority + ", mho=" + mho + "]";
	}

}
