package ru.stroki.test.dto;

import lombok.*;
import ru.stroki.test.entity.User;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String login;
    private String registrationDate;
}
