package ru.stroki.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация о переходах по ссылке")
public class StatisticsDto {
    @Schema(description = "Ссылка")
    private String shortUrl;
    @Schema(description = "Количество переходов")
    private Integer countTransition;
}
