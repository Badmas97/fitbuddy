package app.fitbuddy.dto.history;

import app.fitbuddy.annotation.FitBuddyDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRequestDTO {
	
	@JsonIgnore	
	private Integer appUserId;
	
	@NotBlank
	@Size(min = 1, max = 32)
	private String exerciseName;	
	
	@PositiveOrZero
	private Integer weight;	
	
	@Positive
	private Integer reps;
	
	@FitBuddyDate
	private String createdOn;

}
