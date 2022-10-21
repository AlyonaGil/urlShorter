package ru.stroki.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.stroki.test.dto.RegUserDto;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.UserService;
import ru.stroki.test.mapper.DtoMapper;

import java.net.URI;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/urlShorter")
@Tag(
        name = "Пользователи",
        description = "Методы для работы с пользователями системы"
)
public class UserController {
    private final UserService userService;

    private final DtoMapper dtoMapper;


    @GetMapping("/user")
    @Operation(summary = "Получить информацию о текущем пользователе")
    public ResponseEntity<UserDto> getUser(@RequestAttribute("user") User user) {
        return ResponseEntity.ok(dtoMapper.getUserDto(user));
    }

    @PostMapping("/user")
    @Operation(summary = "Регистрация нового пользователя")
    public ResponseEntity<?> createUser(@RequestBody RegUserDto regUserDto){
        UserDto userDto = userService.createUser(regUserDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{login}")
                .buildAndExpand(regUserDto.getLogin())
                .toUri();
        return ResponseEntity.created(location)
                .body(userDto);
    }
}
