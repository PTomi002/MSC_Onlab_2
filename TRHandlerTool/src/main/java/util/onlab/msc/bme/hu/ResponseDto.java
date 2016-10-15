package util.onlab.msc.bme.hu;

import org.apache.commons.lang3.StringUtils;

public final class ResponseDto<T> {
	private final T value;

	private final Throwable exception;

	private final String reason;

	private final boolean success;

	private ResponseDto(T value, Throwable exception, String reason, boolean success) {
		this.value = value;
		this.exception = exception;
		this.reason = reason;
		this.success = success;
	}

	public static final <T> ResponseDto<T> ok() {
		return new ResponseDto<T>(null, null, StringUtils.EMPTY, true);
	}

	public static final <T> ResponseDto<T> ok(T value) {
		return new ResponseDto<T>(value, null, StringUtils.EMPTY, true);
	}

	public static final <T> ResponseDto<T> fail(String reason) {
		return new ResponseDto<T>(null, null, reason, false);
	}

	public static final <T> ResponseDto<T> fail(String reason, Throwable exception) {
		return new ResponseDto<T>(null, exception, reason, false);
	}

	public T getValue() {
		return value;
	}

	public Throwable getException() {
		return exception;
	}

	public String getReason() {
		return reason;
	}

	public boolean isSuccess() {
		return success;
	}
}
