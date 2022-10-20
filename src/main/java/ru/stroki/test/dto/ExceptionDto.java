package ru.stroki.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация об ошибке")
public class ExceptionDto {
    @Schema(description = "Статус")
    private Integer status;
    @Schema(description = "Сообщение")
    private String message;
}
