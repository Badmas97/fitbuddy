package app.fitbuddy.controller.operation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.fitbuddy.dto.accountinfo.AccountInfoResponseDTO;
import app.fitbuddy.dto.accountinfo.AccountInfoUpdateDTO;
import app.fitbuddy.security.AppUserPrincipal;
import app.fitbuddy.service.operation.AccountInfoService;

@RestController
@RequestMapping("/user/account")
@PreAuthorize("authenticated")
public class AccountInfoController {

	private final AccountInfoService accountInfoService;

	@Autowired
	public AccountInfoController(AccountInfoService accountInfoService) {
		this.accountInfoService = accountInfoService;
	}

	@GetMapping
	public AccountInfoResponseDTO read(@AuthenticationPrincipal AppUserPrincipal appUserPrincipal) {
		// Fetching the account information using the username from the principal
		return accountInfoService.read(appUserPrincipal.getUsername());
	}

	@PutMapping
	public void update(@RequestBody @Valid AccountInfoUpdateDTO accountInfoUpdateDTO,
					   @AuthenticationPrincipal AppUserPrincipal appUserPrincipal) {
		// Updating the account information using the username from the principal
		accountInfoService.update(appUserPrincipal.getUsername(), accountInfoUpdateDTO);
	}
}
