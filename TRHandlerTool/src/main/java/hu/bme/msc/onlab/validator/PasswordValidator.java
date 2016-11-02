package hu.bme.msc.onlab.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hu.bme.msc.onlab.util.Assert;
import hu.bme.msc.onlab.validator.annotation.Password;

public class PasswordValidator extends BaseValidator implements ConstraintValidator<Password, String> {
	private static final String DEFAULT_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,15}$";
	
	/*
	^                 # start-of-string
	(?=.*[0-9])       # a digit must occur at least once
	(?=.*[a-z])       # a lower case letter must occur at least once
	(?=.*[A-Z])       # an upper case letter must occur at least once
	(?=.*[@#$%^&+=])  # a special character must occur at least once
	(?=\S+$)          # no whitespace allowed in the entire string
	.{8,}             # anything, at least eight places though
	$                 # end-of-string
	*/
	
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
