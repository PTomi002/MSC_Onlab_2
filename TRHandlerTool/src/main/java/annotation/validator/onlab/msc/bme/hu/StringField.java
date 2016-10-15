package annotation.validator.onlab.msc.bme.hu;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import validator.onlab.msc.bme.hu.StringFieldValidator;

@Documented
@Constraint(validatedBy = StringFieldValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringField {
	String message() default "{String field is invalid!}";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
