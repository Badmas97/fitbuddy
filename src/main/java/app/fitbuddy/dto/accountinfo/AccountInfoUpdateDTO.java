package app.fitbuddy.dto.accountinfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoUpdateDTO {

	@NotBlank
	@Size(min = 4, max = 15)
	private String oldPassword;

	@NotBlank
	@Size(min = 4, max = 15)
	private String newPassword;
}
