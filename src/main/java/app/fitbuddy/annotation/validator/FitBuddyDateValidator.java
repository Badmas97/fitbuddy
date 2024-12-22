package app.fitbuddy.annotation.validator;

import app.fitbuddy.annotation.FitBuddyDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Validator for the @FitBuddyDate custom annotation.
 */
@Service
public class FitBuddyDateValidator implements ConstraintValidator<FitBuddyDate, String> {

	private DateTimeFormatter dateTimeFormatter;

	/**
	 * Constructor to inject the date format pattern from application properties.
	 *
	 * @param datePattern Default date pattern if not provided in properties is yyyy-MM-dd.
	 */
	public FitBuddyDateValidator(@Value("${fitbuddy.validation.pattern.date:yyyy-MM-dd}") String datePattern) {
		this.dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return true; // Allow null or empty values (optional validation).
		}
		try {
			LocalDate.parse(value, dateTimeFormatter);
			return true;
		} catch (DateTimeParseException ex) {
			return false; // Return false if the date format is invalid.
		}
	}
}
