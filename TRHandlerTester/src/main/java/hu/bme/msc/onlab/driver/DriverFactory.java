package hu.bme.msc.onlab.driver;

import hu.bme.msc.onlab.entity.SUT;

public final class DriverFactory {
	public static PingDriver getPingDriver(SUT sut){
		return new PingDriver(sut);
	}
}
