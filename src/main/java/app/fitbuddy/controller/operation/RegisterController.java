package app.fitbuddy.controller.operation;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.fitbuddy.dto.RegisterDTO;
import app.fitbuddy.service.operation.NewUserService;
import app.fitbuddy.service.operation.RegisterService;

/**
 * Handles user registration requests.
 */
@RestController
public class RegisterController {

	private final Logger logger;
	private final RegisterService registerService;
	private final NewUserService newUserService;

	@Autowired
	public RegisterController(RegisterService registerService, NewUserService newUserService) {
		this.registerService = registerService;
		this.newUserService = newUserService;
		this.logger = LoggerFactory.getLogger(RegisterController.class);
	}

	/**
	 * Registers a new user and assigns default exercises.
	 * @param registerDTO the registration details
	 * @return a response with the registration result
	 */
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
		logger.info("Attempting to register: {}", registerDTO);

		try {
			// Perform the registration process
			Integer appUserId = registerService.register(registerDTO.getName(), registerDTO.getPassword());

			// Assign default exercises to the new user
			newUserService.addDefaultExercises(appUserId);

			// Log and return success message with user ID
			logger.info("User registered successfully with ID: {}", appUserId);
			return ResponseEntity.ok("Registration successful. User ID: " + appUserId);

		} catch (Exception e) {
			// Log error and return a failure response
			logger.error("Registration failed for: {}", registerDTO.getName(), e);
			return ResponseEntity.status(500).body("Registration failed. Please try again.");
		}
	}
}
