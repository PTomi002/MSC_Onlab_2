package hu.bme.msc.onlab.dto;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import hu.bme.msc.onlab.util.SystemFormat;

public class DateTimeAdapter extends XmlAdapter<String, Date> {

	@Override
	public Date unmarshal(String v) throws Exception {
		return SystemFormat.formatDate(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return SystemFormat.parseDate(v);
	}

}
