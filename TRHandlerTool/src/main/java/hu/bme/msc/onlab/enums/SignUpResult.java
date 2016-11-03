package hu.bme.msc.onlab.enums;

public enum SignUpResult {
	PASSED("PASSED"), FAILED("FAILED"), NOT_SIGNUP("NOT_SIGNUP");
	
	private String value;

	private SignUpResult(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
