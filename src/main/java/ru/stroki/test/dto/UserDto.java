package ru.stroki.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.stroki.test.entity.User;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Информация о пользователе")
public class UserDto {
    @Schema(description = "Идентификатор")
    private Integer id;
    @Schema(description = "Логин")
    private String login;
    @Schema(description = "Дата регистрации")
    private String registrationDate;
}
