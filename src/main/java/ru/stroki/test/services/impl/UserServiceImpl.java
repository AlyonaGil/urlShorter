package ru.stroki.test.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.RegUserDto;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.UserRepository;
import ru.stroki.test.services.UserService;
import ru.stroki.test.utils.AuthUtil;
import ru.stroki.test.mapper.DtoMapper;
import ru.stroki.test.validation.ValidationException;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final DtoMapper dtoMapper;

    @Override
    public UserDto createUser(RegUserDto regUserDto){
        String login = regUserDto.getLogin();
        String password = regUserDto.getPassword();
        if (repository.getByLogin(login).isPresent()){
            throw new ValidationException("Пользователь с логином " + login + " уже существует");
        }
        if (password.length() > 25 || password.length() < 5){
            throw new ValidationException("Длина пароля должна быть от 5 до 25 символов");
        }
        if (login.length() > 25 || login.length() < 5){
            throw new ValidationException("Длина логина должна быть от 5 до 25 символов");
        }
        User user = User.builder()
                .login(login)
                .hash(AuthUtil.getHash(login, password))
                .build();
        User userSaved = repository.saveAndFlush(user);
        log.info("Пользователь " + login + " зарегистрирован");
        return dtoMapper.getUserDto(userSaved);
    }

    @Override
    public Optional<User> getByLogin(String login){
        return repository.getByLogin(login);
    }
}
