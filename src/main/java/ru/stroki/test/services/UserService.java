package ru.stroki.test.services;

import ru.stroki.test.dto.UserDto;

public interface UserService {
    UserDto createUser(String login, String password);
    UserDto getByLogin(String login);
}
