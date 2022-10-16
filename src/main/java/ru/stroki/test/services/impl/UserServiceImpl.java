package ru.stroki.test.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.repository.UserRepository;
import ru.stroki.test.services.UserService;
import ru.stroki.test.utils.DtoMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDto createUser(String login, String password){
//        User user = User.builder().build();
//        user.setLogin(login);
//        user.setHash(password);
//        repository.save(user);
        return null;
    }

    @Override
    public UserDto getByLogin(String login){
        return repository.getByLogin(login)
                .map(DtoMapper::getDto)
                .orElse(null);
    }
}
