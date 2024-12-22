package app.fitbuddy.dto.appuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for updating AppUser details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserUpdateDTO {

	@NotBlank
	@Size(min = 4, max = 15)
	private String name;

	// This will be excluded from JSON serialization/deserialization (password should not be included in the update request)
	@JsonIgnore
	@Size(min = 4, max = 15)
	private String password;

	@NotBlank
	@Size(min = 4, max = 16)
	private String rolename;

	// Custom constructor for cases where password is not needed during update
	public AppUserUpdateDTO(String name, String rolename) {
		this.name = name;
		this.rolename = rolename;
	}
}
