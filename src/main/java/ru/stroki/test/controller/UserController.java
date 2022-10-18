package ru.stroki.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.stroki.test.dto.UserDto;
import ru.stroki.test.entity.User;
import ru.stroki.test.services.UserService;
import ru.stroki.test.utils.DtoMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/urlShorter")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DtoMapper dtoMapper;


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
