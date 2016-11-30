package hu.bme.msc.onlab.model.sql;

public enum Priority {
	A("A"), B("B"), C("C");
	
	private String value;
	
	private Priority(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
