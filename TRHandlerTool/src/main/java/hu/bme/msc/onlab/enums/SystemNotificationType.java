package hu.bme.msc.onlab.enums;

public enum SystemNotificationType {
	LDAP_REGISTRAION_STARTED(100, "Notification::LDAP::Registration started"),
	LDAP_REGISTRATION_FINISHED(101, "Notification::LDAP::Registration successfully finished"), 
	LDAP_REGISTRATION_ERROR(199, "Notification::LDAP::Registration unfinished"),
	RMDBS_REGISTRAION_STARTED(200, "Notification::RMDBS::Registration started"),
	RMDBS_REGISTRATION_FINISHED(201, "Notification::RMDBS::Registration successfully finished"),
	RMDBS_REGISTRATION_ERROR(299, "Notification::RMDBS::Registration unfinished");

	private int id;

	private String message;
	
	private SystemNotificationType(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public int getValue() {
		return id;
	}

	public String getMessage() {
		return message;
	}
	
}
