package hu.bme.msc.onlab.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hu.bme.msc.onlab.util.Assert;
import hu.bme.msc.onlab.validator.annotation.Email;

public class EmailValidator extends BaseValidator implements ConstraintValidator<Email, String> {

	private static final String DEFAULT_EMAIL_PATTERN = "([a-zA-Z]{3,15})+(@)([a-zA-Z]{3,15})+(.)([a-zA-Z]{2,5})+";
	
	@Override
	public void initialize(Email constraintAnnotation) {
		// Not required to implement.
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		try {
			Assert.checkAgainstPattern(Pattern.compile(DEFAULT_EMAIL_PATTERN), email);
		} catch (IllegalArgumentException e) {
			LOGGER.warn("Invalid email!", e);
			return false;
		}
		return true;
	}
}
