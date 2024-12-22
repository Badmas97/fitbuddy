package app.fitbuddy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
	
	@NotBlank
	@Size(min = 4, max = 15)
	private final String name;
	
	@NotBlank
	@Size(min = 4, max = 15)
	private final String password;
	
}
