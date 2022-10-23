package ru.stroki.test.services;

import org.springframework.validation.annotation.Validated;
import ru.stroki.test.dto.RegUserDto;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface UserService {
    UserDto createUser(@Valid RegUserDto regUserDto);
    Optional<User> getByLogin(@NotNull @NotBlank String login);
}
