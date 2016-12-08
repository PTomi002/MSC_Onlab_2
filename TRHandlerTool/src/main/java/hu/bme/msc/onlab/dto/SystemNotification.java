package hu.bme.msc.onlab.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import hu.bme.msc.onlab.enums.SystemNotificationType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SystemNotification")
public class SystemNotification implements Serializable {

	private static final long serialVersionUID = 8226260673920389529L;

	@XmlElement
	private Integer id;

	@XmlElement
	private String message;

	public static SystemNotification of(SystemNotificationType systemNotifiction) {
		return of(systemNotifiction.getValue(), systemNotifiction.getMessage());
	}
	
	public static SystemNotification of(SystemNotificationType systemNotifiction, String message) {
		return of(systemNotifiction.getValue(), message);
	}

	public static SystemNotification of(Integer id, String message) {
		return new SystemNotification(id, message);
	}

	public SystemNotification(Integer id, String message) {
		this.id = id;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public SystemNotification setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public SystemNotification setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		return "SystemNotification [id=" + id + ", message=" + message + "]";
	}

}
