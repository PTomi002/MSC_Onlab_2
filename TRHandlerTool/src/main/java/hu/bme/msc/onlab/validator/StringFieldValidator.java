package hu.bme.msc.onlab.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hu.bme.msc.onlab.util.Assert;
import hu.bme.msc.onlab.validator.annotation.StringField;

public class StringFieldValidator extends BaseValidator implements ConstraintValidator<StringField, String> {
	
	private static final String DEFAULT_STRING_PATTERN = "[a-zA-Z0-9]{5,15}";
	
	@Override
	public void initialize(StringField constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			Assert.checkAgainstPattern(Pattern.compile(DEFAULT_STRING_PATTERN), value);
		} catch (IllegalArgumentException e) {
			LOGGER.warn("Invalid value: " + value, e);
			return false;
		}
		return true;
	}
}
