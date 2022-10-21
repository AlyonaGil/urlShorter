package ru.stroki.test.services;

import org.springframework.validation.annotation.Validated;
import ru.stroki.test.dto.RegUserDto;
import ru.stroki.test.dto.UserDto;

import javax.validation.Valid;

@Validated
public interface UserService {
    UserDto createUser(@Valid RegUserDto regUserDto);
    UserDto getUser();
}
