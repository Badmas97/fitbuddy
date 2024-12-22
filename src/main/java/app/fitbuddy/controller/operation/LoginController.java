package app.fitbuddy.controller.operation;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.fitbuddy.dto.LoginDTO;
import app.fitbuddy.service.operation.LoginService;

/**
 * Handles the client's REST API requests that are related to logging in.
 */
@RestController
public class LoginController {

	private final Logger logger;
	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
		this.logger = LoggerFactory.getLogger(LoginController.class);
	}

	/**
	 * Performs login for the user.
	 * @param loginDTO the login request data
	 * @return a response entity with the login status or JWT token
	 */
	@PostMapping("/login/perform_login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
		logger.info("Trying to log in: {}", loginDTO);

		// Call the login service to handle login logic
		boolean isAuthenticated = loginService.login(loginDTO);

		if (isAuthenticated) {
			// Return a response with a success message or JWT token
			return ResponseEntity.ok("Login successful");
		} else {
			// Return a response indicating failure
			return ResponseEntity.status(401).body("Invalid credentials");
		}
	}
}
