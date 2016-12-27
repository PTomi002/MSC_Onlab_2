package hu.bme.msc.onlab.dto;

import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

@XmlRootElement(name = "EVENT")
@XmlType(propOrder = { "root", "date", "message", "additional" })
public class Event {

	private static final String DELIMETER = "::";

	private static final String A_DELIMETER = ":";

	@XmlElement(name = "MESSAGE")
	protected final String message;

	@XmlElement(name = "DATE")
	@XmlJavaTypeAdapter(value = DateTimeAdapter.class)
	protected final Date date;

	@XmlElement(name = "ROOT")
	protected final Class<? extends Event> root = getClass();

	@XmlElement(name = "THREAD")
	protected final String thread = Thread.currentThread().getName();

	@XmlElement(name = "ELEMENT")
	@XmlElementWrapper(name = "ADDITIONAL")
	protected final Map<String, String> additional;

	private Event(EventBuilder eventBuilder) {
		this.message = eventBuilder.message;
		this.date = eventBuilder.date;
		this.additional = Maps.newHashMap(eventBuilder.additional);
	}

	@Override
	public String toString() {
		return "Event [" + root + DELIMETER + date + DELIMETER + message + DELIMETER + additional.entrySet().stream()
				.map(entry -> entry.getKey() + " - " + entry.getValue()).collect(Collectors.joining(A_DELIMETER)) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additional == null) ? 0 : additional.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		result = prime * result + ((thread == null) ? 0 : thread.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (additional == null) {
			if (other.additional != null)
				return false;
		} else if (!additional.equals(other.additional))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		if (thread == null) {
			if (other.thread != null)
				return false;
		} else if (!thread.equals(other.thread))
			return false;
		return true;
	}

	public static class EventBuilder {

		private String message = StringUtils.EMPTY;

		private Date date = new Date();

		private final Map<String, String> additional = Maps.newHashMap();

		private EventBuilder() {
		}

		public static EventBuilder of() {
			return new EventBuilder();
		}

		public EventBuilder setMessage(String message) {
			this.message = message;
			return this;
		}

		public EventBuilder setDate(Date date) {
			this.date = date;
			return this;
		}

		public EventBuilder addAdditional(Supplier<String> key, Supplier<String> value) {
			additional.put(key.get(), value.get());
			return this;
		}

		public EventBuilder addAdditional(Supplier<Map.Entry<String, String>> entry) {
			additional.put(entry.get().getKey(), entry.get().getValue());
			return this;
		}

		public Event build() {
			return new Event(this);
		}

	}
}