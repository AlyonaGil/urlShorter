package ru.stroki.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stroki.test.services.UserService;

@RestController
@RequestMapping("/urlShorter")
public class UserController {
    private UserService userService;
}
