package ru.stroki.test.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.UserService;
import ru.stroki.test.mapper.DtoMapper;

import java.net.URI;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/urlShorter")
public class UserController {
    private final UserService userService;

    private final DtoMapper dtoMapper;


    @GetMapping("/user")
    public ResponseEntity<UserDto> getUser(@RequestAttribute("user") User user) {
        return ResponseEntity.ok(dtoMapper.getDto(user));
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody Map<String, String> params){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{login}")
                .buildAndExpand(params.get("login"))
                .toUri();
        return ResponseEntity.created(location)
                .body(userService.createUser(params.get("login"), params.get("password")));
    }
}
