package hu.bme.msc.onlab.trhandlertool.driver;

import hu.bme.msc.onlab.framework.entity.SUT;

public final class DriverFactory {
	public static PingDriver getPingDriver(SUT sut){
		return new PingDriver(sut);
	}
}
