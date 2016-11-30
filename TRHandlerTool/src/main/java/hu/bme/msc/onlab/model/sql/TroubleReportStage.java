package hu.bme.msc.onlab.model.sql;

public enum TroubleReportStage {
	REGISTERED("R"), ASSIGNED("AS"), PROPOSED("P"), VERIFIED("V"), APPROVED("AD");
	
	private String value;
	
	private TroubleReportStage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
