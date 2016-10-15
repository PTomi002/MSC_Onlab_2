package service.onlab.msc.bme.hu;

import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.onlab.msc.bme.hu.ResponseDto;

public abstract class BaseService {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	protected static <T, R> ResponseDto<R> executeOperation(Supplier<Function<T, ResponseDto<R>>> supplier, T value){
		final Function<T, ResponseDto<R>> operation = supplier.get();
		return executeOperation(operation, value);
	}
	
	protected static <T, R> ResponseDto<R> executeOperation(Function<T, ResponseDto<R>> operation, T value){
		try {
			return operation.apply(value);
		} catch (Exception e) {
			return ResponseDto.fail("Exception happened during executing operation", e);
		}
	}
}
