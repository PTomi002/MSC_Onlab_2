package hu.bme.msc.onlab.model.sql;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "trouble_report")
public class TroubleReport implements Serializable {

	private static final long serialVersionUID = 4952212837033940343L;

	@Id
	@Column(name = "TR_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long troubleReportId;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User actualUser;

	@Lob
	@Column(name = "ANSWER", nullable = true)
	private String answerField;

	@Lob
	@Column(name = "OBSERVATION", nullable = false)
	private String observationField;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_PLANNED_RELEASE_ID", nullable = true)
	private Product plannedReleaseVersion;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_RELEASE_ID", nullable = true)
	private Product releaseVersion;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ISSUED_FOR_ID", nullable = false)
	private Product issuedFor;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "troubleReport")
	private List<History> history;

	@Column(name = "STAGE", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TroubleReportStage stage;

	@Column(name = "PRIORITY", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Priority priority;

	@ManyToOne
	@JoinColumn(name = "MHO_ID", nullable = false)
	private ModificationHandlingOffice mho;

	public TroubleReport() {
		// Needed for Hibernate instantiation.
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
				+ plannedReleaseVersion + ", releaseVersion=" + releaseVersion + ", issuedFor=" + issuedFor + ", stage="
				+ stage + ", priority=" + priority + ", mho=" + mho + "]";
	}

}
