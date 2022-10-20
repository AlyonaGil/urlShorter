package ru.stroki.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.stroki.test.entity.Url;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Полная информация об Url")
public class UrlInfoDto {
    @Schema(description = "Исходный url")
    private String longUrl;
    @Schema(description = "Короткий url")
    private String shortUrl;
    @Schema(description = "Информация о пользователе")
    private UserDto user;
    @Schema(description = "Дата создания")
    private String createDate;
    @Schema(description = "Дата удаления")
    private String deleteDate;
    @Schema(description = "Количество переходов")
    private Integer countOfViews;
}
