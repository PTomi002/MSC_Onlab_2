package util.onlab.msc.bme.hu;

import java.util.regex.Pattern;

public final class Assert {
	private Assert() {
	}
	
	public static void checkAgainstPattern(Pattern pattern, String value) throws IllegalArgumentException {
		if (!pattern.matcher(value).matches()) {
			throw new IllegalArgumentException(
					"The value: " + value + " does not match against pattern: " + pattern.toString() + "!");
		}
	}
}
