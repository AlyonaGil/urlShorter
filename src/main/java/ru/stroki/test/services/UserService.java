package ru.stroki.test.services;

import org.springframework.validation.Errors;
import ru.stroki.test.dto.UserDto;

public interface UserService {
    UserDto createUser(String login, String password);
    UserDto getUser();
}
