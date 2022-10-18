package ru.stroki.test.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.UserRepository;
import ru.stroki.test.services.UserService;
import ru.stroki.test.utils.AuthUtil;
import ru.stroki.test.utils.DtoMapper;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public UserDto createUser(String login, String password){
        //todo проверка корректности строк login и password
        //todo + обработка ошибок
        User user = User.builder()
                .login(login)
                .hash(AuthUtil.getHash(login, password))
                .build();
        User userSaved = repository.saveAndFlush(user);
        return dtoMapper.getDto(userSaved);
    }

    @Override
    public UserDto getUser(){
        String login = "";
        //todo проверка корректности Login
        //todo если пусто, то вернуть ошибку
        return repository.getByLogin(login)
                .map(dtoMapper::getDto)
                .orElse(null);
    }

    public Optional<User> getByLogin(String login){
        //todo проверка корректности Login
        return repository.getByLogin(login);
    }
}
