package hu.bme.msc.onlab.driver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bme.msc.onlab.entity.SUT;

public class PingDriver {
	private static final int DEFAULT_TIMEOUT = 3;

	private static final Logger LOGGER = LoggerFactory.getLogger(PingDriver.class);

	private SUT sut;

	public PingDriver(SUT sut) {
		this.sut = sut;
	}

	public boolean ping() {
		return ping(Duration.ofSeconds(DEFAULT_TIMEOUT));
	}

	public boolean ping(Duration timeout) {
		LOGGER.info("Ping host " + sut.getHost().getHostAddress() + ":" + sut.getPort() + " with timeout: "
				+ timeout.toMillis());
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(sut.getHost(), sut.getPort()), (int) timeout.toMillis());
			return true;
		} catch (IOException e) {
			return false; // Either timeout or unreachable or failed DNS lookup.
		}
	}
}
