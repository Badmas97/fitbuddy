package app.fitbuddy.dto.exercise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for updating Exercise details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseUpdateDTO {

	// Validates that name is not blank and is between 1 and 32 characters
	@NotBlank
	@Size(min = 1, max = 32)
	private String name;
}
