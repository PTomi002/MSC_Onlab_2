package hu.bme.msc.onlab.framework.eventhandling;

import java.util.List;

import hu.bme.msc.onlab.framework.entity.Event;

public class EventHolder {
	private static class EventHolderWrapper {
		static final EventHolder INSTANCE = new EventHolder();
	}

	public static EventHolder getInstance() {
		return EventHolderWrapper.INSTANCE;
	}
	
	private ThreadLocal<List<Event>> eventListRef;
	
	public void addEvent(Event event) {
		List<Event> events = eventListRef.get();
		events.add(event);
		eventListRef.set(events);
	}
}
