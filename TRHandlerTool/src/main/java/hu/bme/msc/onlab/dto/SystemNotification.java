package hu.bme.msc.onlab.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SystemNotification")
public class SystemNotification implements Serializable{

	private static final long serialVersionUID = 8226260673920389529L;

	@XmlElement
	private Integer id;
	
	@XmlElement
	private String message;
	
	public SystemNotification() {
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
