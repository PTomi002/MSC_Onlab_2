package hu.bme.msc.onlab.util;

import java.util.regex.Pattern;

public final class Assert {
	private Assert() {
	}
	
	public static void checkAgainstPattern(Pattern pattern, String value) {
		if (!pattern.matcher(value).matches()) {
			throw new IllegalArgumentException(
					"The value: " + value + " does not match against pattern: " + pattern.toString() + "!");
		}
	}
}
