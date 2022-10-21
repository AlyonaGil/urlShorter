package ru.stroki.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация для регистрации пользователя")
public class RegUserDto {
    @NotNull
    @NotBlank
    @Schema(description = "Логин")
    private String login;
    @NotNull
    @NotBlank
    @Schema(description = "Пароль")
    private String password;
}
