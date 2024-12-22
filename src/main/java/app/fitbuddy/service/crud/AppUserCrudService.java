package app.fitbuddy.service.crud;

import app.fitbuddy.dto.appuser.AppUserRequestDTO;
import app.fitbuddy.dto.appuser.AppUserResponseDTO;
import app.fitbuddy.dto.appuser.AppUserUpdateDTO;
import app.fitbuddy.entity.AppUser;
import app.fitbuddy.exception.FitBuddyException;
import app.fitbuddy.repository.AppUserRepository;
import app.fitbuddy.service.mapper.AppUserMapperService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for CRUD operations on AppUser.
 */
@Service
public class AppUserCrudService implements CrudService<AppUserRequestDTO, AppUserResponseDTO, AppUserUpdateDTO> {

	private final AppUserRepository appUserRepository;
	private final AppUserMapperService appUserMapperService;

	@Autowired
	public AppUserCrudService(AppUserRepository appUserRepository, AppUserMapperService appUserMapperService) {
		this.appUserRepository = appUserRepository;
		this.appUserMapperService = appUserMapperService;
	}

	/**
	 * Creates a new AppUser.
	 *
	 * @param requestDTO Data Transfer Object containing user details for creation.
	 * @return AppUserResponseDTO representing the created user.
	 */
	@Override
	public AppUserResponseDTO create(AppUserRequestDTO requestDTO) {
		if (requestDTO == null) {
			throw new IllegalArgumentException("RequestDTO cannot be null.");
		}

		if (appUserRepository.findByName(requestDTO.getName()).isPresent()) {
			throw new FitBuddyException("Username already exists.");
		}

		AppUser appUser = appUserMapperService.requestDtoToEntity(requestDTO);
		AppUser savedAppUser = appUserRepository.save(appUser);
		return appUserMapperService.entityToResponseDto(savedAppUser);
	}

	/**
	 * Reads a user by their ID.
	 *
	 * @param id User ID.
	 * @return AppUserResponseDTO if found, null otherwise.
	 */
	@Override
	public AppUserResponseDTO readById(Integer id) {
		return appUserRepository.findById(id)
				.map(appUserMapperService::entityToResponseDto)
				.orElse(null);
	}

	/**
	 * Reads a user by their username.
	 *
	 * @param name Username.
	 * @return AppUserResponseDTO if found, null otherwise.
	 */
	public AppUserResponseDTO readByName(String name) {
		return appUserRepository.findByName(name)
				.map(appUserMapperService::entityToResponseDto)
				.orElse(null);
	}

	/**
	 * Reads all users.
	 *
	 * @return List of AppUserResponseDTO representing all users.
	 */
	@NotNull
	public List<AppUserResponseDTO> readAll() {
		List<AppUser> users = (List<AppUser>) appUserRepository.findAll();
		return appUserMapperService.entitiesToResponseDtos(users);
	}

	/**
	 * Updates a user with new details.
	 *
	 * @param id        User ID.
	 * @param updateDTO Data Transfer Object containing updated details.
	 * @return AppUserResponseDTO if updated successfully, null otherwise.
	 */
	@Override
	public AppUserResponseDTO update(Integer id, AppUserUpdateDTO updateDTO) {
		if (updateDTO == null) {
			throw new IllegalArgumentException("UpdateDTO cannot be null.");
		}

		Optional<AppUser> optionalAppUser = appUserRepository.findById(id);
		if (optionalAppUser.isEmpty()) {
			throw new FitBuddyException("User not found for ID: " + id);
		}

		AppUser existingAppUser = optionalAppUser.get();

		// Check if the username is being updated to an already existing one
		if (!existingAppUser.getName().equals(updateDTO.getName()) &&
				appUserRepository.findByName(updateDTO.getName()).isPresent()) {
			throw new FitBuddyException("Username already exists.");
		}

		AppUser updatedAppUser = appUserMapperService.applyUpdateDtoToEntity(existingAppUser, updateDTO);
		AppUser savedAppUser = appUserRepository.save(updatedAppUser);

		return appUserMapperService.entityToResponseDto(savedAppUser);
	}

	/**
	 * Deletes a user by their ID.
	 *
	 * @param id User ID.
	 */
	@Override
	public void delete(Integer id) {
		if (!appUserRepository.existsById(id)) {
			throw new FitBuddyException("User not found for ID: " + id);
		}
		appUserRepository.deleteById(id);
	}
}
