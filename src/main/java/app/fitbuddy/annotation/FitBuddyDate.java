package app.fitbuddy.annotation;

import app.fitbuddy.annotation.validator.FitBuddyDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating date formats.
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FitBuddyDateValidator.class)
public @interface FitBuddyDate {

	/**
	 * Error message returned when the date format is invalid.
	 */
	String message() default "Invalid date format. Expected format is yyyy-MM-dd.";

	/**
	 * Validation groups for categorizing constraints.
	 */
	Class<?>[] groups() default {};

	/**
	 * Payload type for carrying additional metadata for the validation.
	 */
	Class<? extends Payload>[] payload() default {};
}
