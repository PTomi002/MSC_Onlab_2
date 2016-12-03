package hu.bme.msc.onlab.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public final class Closer {

	public static void close(List<Closeable> objects) {
		if (objects != null) {
			objects.stream().forEach((object) -> {
				close(object);
			});
		}
	}

	public static void close(Closeable object) {
		if (object != null) {
			try {
				object.close();
			} catch (IOException e) {
			}
		}
	}
}
