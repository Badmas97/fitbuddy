package app.fitbuddy.dto.history;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryUpdateDTO {
	
	@PositiveOrZero
	private Integer weight;
	
	@Positive
	private Integer reps;

}
