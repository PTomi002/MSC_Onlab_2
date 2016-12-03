package hu.bme.msc.onlab.util;

import hu.bme.msc.onlab.entity.SUT;

public final class SUTHolder {
	private static SUTHolder sutHolder = null;

	private SUT sut;

	private SUTHolder(SUT sut) {
		this.sut = sut;
	}
	public static synchronized SUTHolder getInstance(SUT sut) {
		if (sutHolder == null) {
			sutHolder = new SUTHolder(sut);
		}
		return sutHolder;
	}
	
	public static synchronized SUTHolder getIstance() {
		if (sutHolder == null) {
			throw new RuntimeException("SUT holder is null, first initialize it!");
		}
		return sutHolder;
	}
	
	public SUT getSUT() {
		if (sut == null) {
			throw new RuntimeException("SUT is null, first initialize it!");
		}
		return sut;
	}
}
