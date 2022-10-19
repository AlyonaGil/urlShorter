package ru.stroki.test.services;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import ru.stroki.test.dto.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public interface UserService {
    UserDto createUser(@NotNull @NotBlank String login, @NotNull @NotBlank String password);
    UserDto getUser();
}
