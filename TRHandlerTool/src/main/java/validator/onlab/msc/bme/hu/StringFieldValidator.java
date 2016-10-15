package validator.onlab.msc.bme.hu;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import annotation.validator.onlab.msc.bme.hu.StringField;
import util.onlab.msc.bme.hu.Assert;

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
			LOGGER.warn("Invalid username: " + value, e);
			return false;
		}
		return true;
	}
}
