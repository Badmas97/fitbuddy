package app.fitbuddy.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for creating new Exercise.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRequestDTO {

	// Ensures that the name is between 1 and 32 characters
	@NotBlank
	@Size(min = 1, max = 32)
	private String name;

	// This will be excluded from JSON serialization/deserialization
	@JsonIgnore
	private Integer appUserId;
}
