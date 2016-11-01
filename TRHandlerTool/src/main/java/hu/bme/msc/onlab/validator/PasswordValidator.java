package hu.bme.msc.onlab.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hu.bme.msc.onlab.util.Assert;
import hu.bme.msc.onlab.validator.annotation.Password;

public class PasswordValidator extends BaseValidator implements ConstraintValidator<Password, String> {
	private static final String DEFAULT_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,15}$";
	
	@Override
	public void initialize(Password constraintAnnotation) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		try {
			Assert.checkAgainstPattern(Pattern.compile(DEFAULT_PASSWORD_PATTERN), password);
		} catch (IllegalArgumentException e) {
			LOGGER.warn("Invalid password!", e);
			return false;
		}
		return true;
	}

}
