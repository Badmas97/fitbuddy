package app.fitbuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security related configuration.
 */
@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// User's page
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/console/**").permitAll()
				.requestMatchers("/login", "/login/perform_login", "/logout", "/register", "/version", "/public/**").permitAll()
				.anyRequest().authenticated()
		);

		// H2 Console
		http.headers().frameOptions().sameOrigin();

		// Access denied for Admin's page
		http.exceptionHandling().accessDeniedPage("/403");

		// Configure custom login form
		http.formLogin()
				.loginPage("/login"); // Sets the login page URL

		return http.build();
	}

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
	}
}
